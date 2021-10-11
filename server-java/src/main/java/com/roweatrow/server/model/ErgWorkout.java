package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class ErgWorkout {
    @Id
    private long ergWorkout;

    private Instant date;

    @ManyToOne
    private Athlete athlete;
}
