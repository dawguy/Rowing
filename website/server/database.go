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

func InitDB(d *Database) *pgxpool.Pool{
	url := fmt.Sprintf("postgres://%s:%s@%s:%s/%s", d.username, d.password, d.Addr, d.Port, d.databaseName)
	dbpool, err := pgxpool.Connect(context.Background(), url)
	if err != nil {
		fmt.Fprint(os.Stderr, "Unable to connect to datababse: %v\n", err)
		os.Exit(1)
	}

	db = dbpool
	return db
}

func QueryErgWorkouts() []common.ErgWorkout {
	rows, err := db.Query(context.Background(), "select ergworkout, date, athlete from ErgWorkout")

	if err != nil {
		fmt.Errorf("Error selecting ergWorkouts %s", err)
	}

	defer rows.Close()

	ergWorkouts := make([]common.ErgWorkout, 0)

	for rows.Next() {
		var ergWorkout common.ErgWorkout
		err := rows.Scan(&ergWorkout.ErgWorkout, &ergWorkout.Date, &ergWorkout.Athlete)

		if err != nil {
			fmt.Errorf("Error scanning ergWorkout %s", err)
		} else {
			ergWorkouts = append(ergWorkouts, ergWorkout)
		}
	}

	return ergWorkouts
}

func QueryWaterWorkouts() []common.WaterWorkout {
	rows, err := db.Query(context.Background(), "select waterworkout, date, boat from WaterWorkout")

	if err != nil {
		fmt.Errorf("Error selecting waterWorkouts %s", err)
	}

	defer rows.Close()

	waterWorkouts := make([]common.WaterWorkout, 0)

	for rows.Next() {
		var waterWorkout common.WaterWorkout
		err := rows.Scan(&waterWorkout.WaterWorkout, &waterWorkout.Date, &waterWorkout.Boat)

		if err != nil {
			fmt.Errorf("Error scanning waterWorkout %s", err)
		} else {
			waterWorkouts = append(waterWorkouts, waterWorkout)
		}
	}

	return waterWorkouts
}

func queryErgSplits(ergWorkoutId int) []common.ErgSplit {
	rows, err := db.Query(context.Background(), "select ergsplit, seq, duration, distance, heartrate, power, ergworkout from ergSplit where ergworkout = ?", ergWorkoutId)

	if err != nil {
		fmt.Errorf("Error selecting ergSplits %s", err)
	}

	defer rows.Close()

	ergSplits := make([]common.ErgSplit, 0)

	for rows.Next() {
		var ergSplit common.ErgSplit
		err := rows.Scan(&ergSplit.ErgSplit, &ergSplit.Seq, &ergSplit.Duration, &ergSplit.Distance, &ergSplit.HeartRate, &ergSplit.Power)

		if err != nil {
			fmt.Errorf("Error scanning ergSplit %s", err)
		} else {
			ergSplits = append(ergSplits, ergSplit)
		}
	}

	return ergSplits
}

func queryWaterSplits(waterWorkoutId int) []common.WaterSplit {
	rows, err := db.Query(context.Background(), "select watersplit, seq, duration, distance, heartrate, power, withflow, flowrate, waterworkout from waterSplit where waterworkout = ?", waterWorkoutId)

	if err != nil {
		fmt.Errorf("Error selecting waterSplits %s", err)
	}

	defer rows.Close()

	waterSplits := make([]common.WaterSplit, 0)

	for rows.Next() {
		var waterSplit common.WaterSplit
		err := rows.Scan(&waterSplit.WaterSplit, &waterSplit.Seq, &waterSplit.Duration, &waterSplit.Distance, &waterSplit.HeartRate, &waterSplit.Power, &waterSplit.WithFlow, &waterSplit.FlowRate)

		if err != nil {
			fmt.Errorf("Error scanning waterSplit %s", err)
		} else {
			waterSplits = append(waterSplits, waterSplit)
		}
	}

	return waterSplits
}