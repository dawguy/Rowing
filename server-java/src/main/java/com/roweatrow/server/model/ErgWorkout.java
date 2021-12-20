package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ErgWorkout implements Workout{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ergWorkout;

    private Instant date;

    @OneToMany(mappedBy = "ergWorkout")
    private List<ErgSplit> ergSplits = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "athlete")
    private Athlete athlete;

    public List<? extends Split> getSplits(){
        return this.ergSplits;
    }
}
