package com.roweatrow.server.models;

import javax.persistence.*;

@Entity
@Table(name = "water_workout_athlete_split")
public class WaterWorkoutAthleteSplit {
    @Id
    @Column(name = "water_workout_athlete_split")
    private Long waterWorkoutAthleteSplit;

    @Column(name = "water_split")
    private Long waterSplit;

    @Column(name = "athlete")
    private Long athlete;

    @Column(name = "heart_rate")
    private Long heartRate;

    @Column(name = "power")
    private Long power;

    public Long getWaterWorkoutAthleteSplit() {
        return this.waterWorkoutAthleteSplit;
    }

    public void setWaterWorkoutAthleteSplit(Long waterWorkoutAthleteSplit) {
        this.waterWorkoutAthleteSplit = waterWorkoutAthleteSplit;
    }

    public Long getWaterSplit() {
        return this.waterSplit;
    }

    public void setWaterSplit(Long waterSplit) {
        this.waterSplit = waterSplit;
    }

    public Long getAthlete() {
        return this.athlete;
    }

    public void setAthlete(Long athlete) {
        this.athlete = athlete;
    }

    public Long getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(Long heartRate) {
        this.heartRate = heartRate;
    }

    public Long getPower() {
        return this.power;
    }

    public void setPower(Long power) {
        this.power = power;
    }
}
