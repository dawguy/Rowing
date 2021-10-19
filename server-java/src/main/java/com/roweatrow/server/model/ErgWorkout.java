package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class ErgWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ergWorkout;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "athlete")
    private Athlete athlete;
}
