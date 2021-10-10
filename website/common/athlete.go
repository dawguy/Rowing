package common

import (
	"context"
	"fmt"
	"github.com/jackc/pgx/v4/pgxpool"
	"time"
)

type Athlete struct {
	Athlete int
	Name string
	Birthday time.Time
}

type AthleteModel struct {
	 DB *pgxpool.Pool
}

func (m AthleteModel) All() ([]Athlete, error){
	rows, err := m.DB.Query(context.Background(), "select athlete, name, birthday from athlete")

	if err != nil {
		fmt.Errorf("Error selecting athletes %s", err)
		return nil, err
	}

	defer rows.Close()

	athletes := make([]Athlete, 0)

	for rows.Next() {
		var athlete Athlete
		err := rows.Scan(&athlete.Athlete, &athlete.Name, &athlete.Birthday)

		if err != nil {
			fmt.Errorf("Error scanning athlete %s", err)
		} else {
			athletes = append(athletes, athlete)
		}
	}

	if err = rows.Err(); err != nil {
		return nil, err
	}

	return athletes, nil
}