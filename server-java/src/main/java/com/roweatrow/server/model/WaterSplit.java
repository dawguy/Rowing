package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

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
    private boolean withFlow;
    private boolean flowRate;
}
