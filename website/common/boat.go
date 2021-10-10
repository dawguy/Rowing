package common

import (
	"context"
	"fmt"
	"github.com/jackc/pgx/v4/pgxpool"
)

type Boat struct {
	Boat int
	Shell Shell
	Coxswain Athlete
	Seat8 Athlete
	Seat7 Athlete
	Seat6 Athlete
	Seat5 Athlete
	Seat4 Athlete
	Seat3 Athlete
	Seat2 Athlete
	Seat1 Athlete
}

type BoatModel struct {
	DB *pgxpool.Pool
}

func (m BoatModel) All() ([]Boat, error){
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