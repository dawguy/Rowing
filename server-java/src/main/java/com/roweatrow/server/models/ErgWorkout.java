package com.roweatrow.server.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "erg_workout")
public class ErgWorkout implements Workout {
    @Id
    @Column(name = "erg_workout")
    private Long ergWorkout;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "athlete")
    private Long athlete;

    public Long getErgWorkout() {
        return this.ergWorkout;
    }

    public void setErgWorkout(Long ergWorkout) {
        this.ergWorkout = ergWorkout;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Long getAthlete() {
        return this.athlete;
    }

    public void setAthlete(Long athlete) {
        this.athlete = athlete;
    }

    public List<? extends Split> getSplits(){
        return new ArrayList<>();
    }
}
