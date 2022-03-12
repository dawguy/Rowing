package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties(
    ignoreUnknown = true,
    value = {"templateWorkout", "workout"})
public class TemplateSplit implements Split<TemplateWorkout> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "template_split")
  private Long templateSplit;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "template_workout")
  private TemplateWorkout templateWorkout;

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

  public TemplateWorkout getTemplateWorkout() {
    return this.templateWorkout;
  }

  public void setTemplateWorkout(TemplateWorkout templateWorkout) {
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

  public TemplateWorkout getWorkout() {
    return getTemplateWorkout();
  }

  public void setWorkout(TemplateWorkout workout) {
    setTemplateWorkout(workout);
  }
}
