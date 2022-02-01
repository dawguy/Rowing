package com.roweatrow.server.models;

import javax.persistence.*;

@Entity
@Table(name = "water_workout")
public class WaterWorkout {
    @Id
    @Column(name = "water_workout")
    private Long waterWorkout;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "boat")
    private Long boat;

    public Long getWaterWorkout() {
        return this.waterWorkout;
    }

    public void setWaterWorkout(Long waterWorkout) {
        this.waterWorkout = waterWorkout;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Long getBoat() {
        return this.boat;
    }

    public void setBoat(Long boat) {
        this.boat = boat;
    }
}
