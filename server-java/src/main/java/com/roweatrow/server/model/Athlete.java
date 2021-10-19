package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Embeddable
@Getter
@Setter
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long athlete;

    private String name;
    private Instant birthday;

    @Override
    public String toString(){
        return String.format("Athlete: %d, Name: %s, Birthday: %s", athlete, name, birthday);
    }
}
