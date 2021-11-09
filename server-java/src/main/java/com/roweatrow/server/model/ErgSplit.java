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

    @ManyToOne
    private ErgWorkout ergWorkout;

    private int seq;
    private Duration duration;
    private int distance;
    private int heartRate;
    private int power; // In watts
}
