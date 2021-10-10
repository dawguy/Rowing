package main

import (
	"fmt"
	"github.com/dawguy/rowing/website/common"
	Database "github.com/dawguy/rowing/website/server"
)

type Env struct {
	athletes common.AthleteModel
}

func main() {
	db := Database.NewDatabase(
		Database.WithPassword("password"),
		Database.WithUsername("postgres"),
		Database.WithDBAddr("127.0.0.1"),
		Database.WithDatabaseName("test2"),
		Database.WithDBPort("5432"),
	)

	pool := Database.InitDB(db)

	env := &Env{
		athletes: common.AthleteModel{DB: pool},
	}

	athletes, _ := env.athletes.All()
	boats := Database.QueryBoats()
	ergWorkouts := Database.QueryErgWorkouts()
	waterWorkouts := Database.QueryWaterWorkouts()

	fmt.Printf("Athletes: %v\n", athletes)
	fmt.Printf("Boats: %v\n", boats)
	fmt.Printf("Erg Workouts: %v\n", ergWorkouts)
	fmt.Printf("Water Workouts: %v\n", waterWorkouts)
}