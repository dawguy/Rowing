package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Getter
@Setter
public class ErgSplit implements Split{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ergSplit;

    @ManyToOne()
    @JoinColumn(name = "ergWorkout")
    private ErgWorkout ergWorkout;

    private int seq;
    private Duration duration;
    private int distance;
    private Integer heartRate;
    private Integer power; // In watts
}
