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
    @JoinColumn(name = "athlete")
    private Athlete athlete;
}
