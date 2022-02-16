package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "template_split")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateSplit implements Split {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @Column(name = "template_split")
  private Long templateSplit;

  @Column(name = "template_workout")
  private Long templateWorkout;

  @Column(name = "seq")
  private Long seq;

  @Column(name = "duration")
  private Timestamp duration;

  @Column(name = "distance")
  private Long distance;

  public Long getTemplateSplit() {
    return this.templateSplit;
  }

  public void setTemplateSplit(Long templateSplit) {
    this.templateSplit = templateSplit;
  }

  public Long getTemplateWorkout() {
    return this.templateWorkout;
  }

  public void setTemplateWorkout(Long templateWorkout) {
    this.templateWorkout = templateWorkout;
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

  public Long getWorkout() {
    return getTemplateWorkout();
  }

  public void setWorkout(Long workout){
    setTemplateWorkout(workout);
  }
}
