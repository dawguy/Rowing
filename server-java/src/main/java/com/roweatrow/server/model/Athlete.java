package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Athlete {
    @Id
    private Long athlete;

    private String name;
    private Instant birthday;
}
