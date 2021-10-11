package com.roweatrow.server.model;

import javax.persistence.*;

@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long boat;

    @ManyToOne
    private Shell shell;

    @ManyToOne
    private Athlete coxswain;
    @ManyToOne
    private Athlete seat_8;
    @ManyToOne
    private Athlete seat_7;
    @ManyToOne
    private Athlete seat_6;
    @ManyToOne
    private Athlete seat_5;
    @ManyToOne
    private Athlete seat_4;
    @ManyToOne
    private Athlete seat_3;
    @ManyToOne
    private Athlete seat_2;
    @ManyToOne
    private Athlete seat_1;
}
