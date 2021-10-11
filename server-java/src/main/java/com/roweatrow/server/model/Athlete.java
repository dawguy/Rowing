package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
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
