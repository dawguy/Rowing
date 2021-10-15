package com.roweatrow.server.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class WaterWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterWorkout;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "boat")
    private Boat boat;
}
