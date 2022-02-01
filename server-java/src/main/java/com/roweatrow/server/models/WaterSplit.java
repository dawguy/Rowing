package com.roweatrow.server.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "water_split")
public class WaterSplit {
    @Id
    @Column(name = "water_split")
    private Long waterSplit;

    @Column(name = "water_workout")
    private Long waterWorkout;

    @Column(name = "seq")
    private Long seq;

    @Column(name = "duration")
    private Timestamp duration;

    @Column(name = "distance")
    private Long distance;

    @Column(name = "heart_rate")
    private Long heartRate;

    @Column(name = "power")
    private Long power;

    @Column(name = "with_flow")
    private Boolean withFlow;

    @Column(name = "flow_rate")
    private Long flowRate;

    public Long getWaterSplit() {
        return this.waterSplit;
    }

    public void setWaterSplit(Long waterSplit) {
        this.waterSplit = waterSplit;
    }

    public Long getWaterWorkout() {
        return this.waterWorkout;
    }

    public void setWaterWorkout(Long waterWorkout) {
        this.waterWorkout = waterWorkout;
    }

    public Long getSeq() {
        return this.seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Timestamp getDuration() {
        return this.duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public Long getDistance() {
        return this.distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(Long heartRate) {
        this.heartRate = heartRate;
    }

    public Long getPower() {
        return this.power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Boolean getWithFlow() {
        return this.withFlow;
    }

    public void setWithFlow(Boolean withFlow) {
        this.withFlow = withFlow;
    }

    public Long getFlowRate() {
        return this.flowRate;
    }

    public void setFlowRate(Long flowRate) {
        this.flowRate = flowRate;
    }
}
