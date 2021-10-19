package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class WaterWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterWorkout;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "boat")
    private Boat boat;
}
