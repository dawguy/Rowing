package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "water_workout")
public class WaterWorkout implements Workout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @Column(name = "water_workout")
  private Long waterWorkout;

  @Column(name = "date")
  private java.sql.Date date;

  @Column(name = "boat")
  private Long boat;

  @Column(name = "assigned_workout")
  private Long assignedWorkout;

  @OneToMany(mappedBy = "waterWorkout")
  private List<WaterSplit> waterSplits = new ArrayList<>();

  public Long getWaterWorkout() {
    return this.waterWorkout;
  }

  public void setWaterWorkout(Long waterWorkout) {
    this.waterWorkout = waterWorkout;
  }

  public java.sql.Date getDate() {
    return this.date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  public Long getBoat() {
    return this.boat;
  }

  public void setBoat(Long boat) {
    this.boat = boat;
  }

  public Long getAssignedWorkout() {
    return this.assignedWorkout;
  }

  public void setAssignedWorkout(Long assignedWorkout) {
    this.assignedWorkout = assignedWorkout;
  }

  public List<? extends Split> getSplits() {
    return this.waterSplits;
  }
}
