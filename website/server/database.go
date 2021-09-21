package server

import (
	"context"
	"fmt"
	"github.com/dawguy/rowing/website/common"
	"github.com/jackc/pgx/v4/pgxpool"
	"os"
)

type Database struct {
	Port string
	Addr string
	username string
	password string
	databaseName string
}

type DatabaseOption func(database *Database)

var db *pgxpool.Pool

func WithDBPort(p string) DatabaseOption{
	return func(d *Database) {
		d.Port = p
	}
}

func WithDBAddr(a string) DatabaseOption{
	return func(d *Database) {
		d.Addr = a
	}
}

func WithPassword(p string) DatabaseOption{
	return func(d *Database) {
		d.password = p
	}
}

func WithUsername(u string) DatabaseOption{
	return func(d *Database) {
		d.username = u
	}
}

func WithDatabaseName(n string) DatabaseOption{
	return func(d *Database) {
		d.databaseName = n
	}
}

func NewDatabase(opts ...DatabaseOption) *Database {
	const (
		defaultPort = "3000"
		defaultAddr = "127.0.0.1"
		defaultUsername = "postgres"
		defaultPassword = "postgres"
		defaultDatabaseName = "rowing"
	)
	d := &Database{
		Port: defaultPort,
		Addr: defaultAddr,
		username: defaultUsername,
		password: defaultPassword,
		databaseName: defaultDatabaseName,
	}

	for _, opt := range opts {
		opt(d)
	}

	return d
}

func InitDB(d *Database){
	url := fmt.Sprintf("postgres://%s:%s@%s:%s/%s", d.username, d.password, d.Addr, d.Port, d.databaseName)
	dbpool, err := pgxpool.Connect(context.Background(), url)
	if err != nil {
		fmt.Fprint(os.Stderr, "Unable to connect to datababse: %v\n", err)
		os.Exit(1)
	}

	db = dbpool
}

func QueryBoats() []common.Boat {
	rows, err := db.Query(context.Background(), "select * from boat")

	if err != nil {
		fmt.Errorf("Error selecting boats %s", err)
	}

	defer rows.Close()

	boats := make([]common.Boat, 0)

	for rows.Next() {
		var boat common.Boat

		err = rows.Scan(&boat.Boat, &boat.Shell, &boat.Coxswain, &boat.Seat8, &boat.Seat7, &boat.Seat6, &boat.Seat5, &boat.Seat4, &boat.Seat3, &boat.Seat2, &boat.Seat1)
		if err != nil {
			fmt.Errorf("Error scanning boat %s", err)
		} else {
			boats = append(boats, boat)
		}
	}

	return boats
}


func QueryAthletes() []common.Athlete {
	rows, err := db.Query(context.Background(), "select athlete, name, birthday from athlete")

	if err != nil {
		fmt.Errorf("Error selecting athletes %s", err)
	}

	defer rows.Close()

	athletes := make([]common.Athlete, 0)

	for rows.Next() {
		var athlete common.Athlete
		err := rows.Scan(&athlete.Athlete, &athlete.Name, &athlete.Birthday)

		if err != nil {
			fmt.Errorf("Error scanning athlete %s", err)
		} else {
			athletes = append(athletes, athlete)
		}
	}

	return athletes
}