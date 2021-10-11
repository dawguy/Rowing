package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class WaterSplit {
    @Id
    private long waterSplit;

    @ManyToOne
    private WaterWorkout waterWorkout;

    private int seq;
    private Instant duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
    private boolean withFlow;
    private boolean flowRate;
}
