package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class WaterWorkout implements Workout{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long waterWorkout;

    private Instant date;

    @OneToMany(mappedBy = "waterWorkout")
    private List<WaterSplit> waterSplits = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "boat")
    private Boat boat;

    public List<? extends Split> getSplits(){
        return this.waterSplits;
    }
}
