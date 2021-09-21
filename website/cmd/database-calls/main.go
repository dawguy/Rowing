package main

import (
	"fmt"
	Database "github.com/dawguy/rowing/website/server"
)

func main() {
	db := Database.NewDatabase(
		Database.WithPassword("password"),
		Database.WithUsername("postgres"),
		Database.WithDBAddr("127.0.0.1"),
		Database.WithDatabaseName("test2"),
		Database.WithDBPort("5432"),
	)

	Database.InitDB(db)

	athletes := Database.QueryAthletes()
	boats := Database.QueryBoats()

	fmt.Printf("Athletes: %v\n", athletes)
	fmt.Printf("Boats: %v\n", boats)

}