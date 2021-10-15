package com.roweatrow.server.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class WaterSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterSplit;

    @ManyToOne
    @JoinColumn(name = "waterWorkout")
    private WaterWorkout waterWorkout;

    private int seq;
    private Instant duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
    private boolean withFlow;
    private boolean flowRate;
}
