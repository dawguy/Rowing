package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "water_split")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"waterWorkout", "workout"})
public class WaterSplit implements Split<WaterWorkout> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "water_split")
  private Long waterSplit;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "water_workout", insertable = false, updatable = false)
  private WaterWorkout waterWorkout;

  @Column(name = "seq")
  private Long seq;

  @Column(name = "duration")
  private Timestamp duration;

  @Column(name = "distance")
  private Long distance;

  @Column(name = "with_flow")
  private Boolean withFlow;

  @Column(name = "flow_rate")
  private Long flowRate;

  @OneToMany(mappedBy = "waterSplit", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<WaterWorkoutAthleteSplit> waterWorkoutAthleteSplit = new ArrayList<>();

  public Long getWaterSplit() {
    return this.waterSplit;
  }

  public void setWaterSplit(Long waterSplit) {
    this.waterSplit = waterSplit;
  }

  public WaterWorkout getWaterWorkout() {
    return this.waterWorkout;
  }

  public void setWaterWorkout(WaterWorkout waterWorkout) {
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

  @Override
  public Long getHeartRate() {
    return (long)
        this.waterWorkoutAthleteSplit.stream()
            .map(WaterWorkoutAthleteSplit::getHeartRate)
            .filter(Objects::nonNull) // Just ignore missing data. Don't treat as 0.
            .mapToDouble(d -> (double) d)
            .average()
            .orElse(0.0);
  }

  @Override
  public Long getPower() {
    return (long)
        this.waterWorkoutAthleteSplit.stream()
            .map(WaterWorkoutAthleteSplit::getPower)
            .filter(Objects::nonNull) // Just ignore missing data. Don't treat as 0.
            .mapToDouble(d -> (double) d)
            .average()
            .orElse(0.0);
  }

  public WaterWorkout getWorkout() {
    return getWaterWorkout();
  }

  public void setWorkout(WaterWorkout workout) {
    setWaterWorkout(workout);
  }
}
