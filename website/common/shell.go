package common

import "github.com/jackc/pgx/v4/pgxpool"

type Shell struct {
	Shell int
	Name string
}

type ShellModel struct {
	DB *pgxpool.Pool
}

func (m ShellModel) All() ([]Shell, error){

}