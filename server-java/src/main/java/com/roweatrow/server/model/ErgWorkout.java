package com.roweatrow.server.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class ErgWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ergWorkout;

    private Instant date;

    @ManyToOne
    private Athlete athlete;
}
