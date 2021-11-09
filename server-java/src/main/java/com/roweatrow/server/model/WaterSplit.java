package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Getter
@Setter
public class WaterSplit implements Split {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterSplit;

    @ManyToOne
    @JoinColumn(name = "waterWorkout")
    private WaterWorkout waterWorkout;

    private int seq;
    private Duration duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
    private boolean withFlow;
    private boolean flowRate;
}
