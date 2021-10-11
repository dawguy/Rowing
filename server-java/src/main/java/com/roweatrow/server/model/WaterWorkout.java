package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class WaterWorkout {
    @Id
    private long waterWorkout;

    private Instant date;

    @ManyToOne
    private Boat boat;
}
