package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Getter
@Setter
public class WaterWorkoutAthleteSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterWorkoutAthleteSplit;

    @ManyToOne
    @JoinColumn(name = "waterSplit")
    private WaterSplit waterSplit;

    private Integer heartRate;
    private Integer power; // In watts
}
