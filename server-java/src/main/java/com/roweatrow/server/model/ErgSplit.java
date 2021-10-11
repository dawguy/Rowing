package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class ErgSplit {
    @Id
    private long ergSplit;

    @ManyToOne
    private ErgWorkout ergWorkout;

    private int seq;
    private Instant duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
}
