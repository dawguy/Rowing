package common

import (
	"time"
)

type ErgWorkout struct {
	ErgWorkout int
	Date time.Time
	Athlete Athlete
	Splits []ErgSplit // implements Split
}

type WaterWorkout struct {
	WaterWorkout int
	Date time.Time
	Boat Boat
	Splits []WaterSplit // implements Split
}

type ErgSplit struct {
	ErgSplit int
	Seq int
	Duration time.Duration
	Distance int
	HeartRate int
	Power int
}

type WaterSplit struct {
	WaterSplit int
	Seq int
	Duration time.Duration
	Distance int
	HeartRate int
	Power int
	WithFlow bool
	FlowRate int
}

type Workout interface {

}

type Split interface {

}