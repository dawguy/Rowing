package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "erg_split")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"ergWorkout", "workout"})
public class ErgSplit implements Split<ErgWorkout> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "erg_split")
  private Long ergSplit;

  @ManyToOne()
  @JoinColumn(name = "erg_workout", insertable = false, updatable = false)
  private ErgWorkout ergWorkout;

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

  public Long getErgSplit() {
    return this.ergSplit;
  }

  public void setErgSplit(Long ergSplit) {
    this.ergSplit = ergSplit;
  }

  public ErgWorkout getErgWorkout() {
    return this.ergWorkout;
  }

  public void setErgWorkout(ErgWorkout ergWorkout) {
    this.ergWorkout = ergWorkout;
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

  public ErgWorkout getWorkout() {
    return getErgWorkout();
  }

  public void setWorkout(ErgWorkout workout) {
    setErgWorkout(workout);
  }
}
