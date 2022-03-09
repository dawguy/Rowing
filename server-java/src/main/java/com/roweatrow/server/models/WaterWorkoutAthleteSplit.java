package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "water_workout_athlete_split")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"waterSplit"})
public class WaterWorkoutAthleteSplit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "water_workout_athlete_split")
  private Long waterWorkoutAthleteSplit;

  @Column(name = "athlete")
  private Long athlete;

  @Column(name = "heart_rate")
  private Long heartRate;

  @Column(name = "power")
  private Long power;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "water_split")
  private WaterSplit waterSplit;

  public Long getWaterWorkoutAthleteSplit() {
    return this.waterWorkoutAthleteSplit;
  }

  public void setWaterWorkoutAthleteSplit(Long waterWorkoutAthleteSplit) {
    this.waterWorkoutAthleteSplit = waterWorkoutAthleteSplit;
  }

  public Long getAthlete() {
    return this.athlete;
  }

  public void setAthlete(Long athlete) {
    this.athlete = athlete;
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

  public WaterSplit getWaterSplit() {
    return this.waterSplit;
  }

  public void setWaterSplit(WaterSplit waterSplit){
    this.waterSplit = waterSplit;
  }
}
