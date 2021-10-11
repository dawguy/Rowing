package com.roweatrow.server.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class ErgSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ergSplit;

    @ManyToOne
    private ErgWorkout ergWorkout;

    private int seq;
    private Instant duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
}
