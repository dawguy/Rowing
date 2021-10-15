package com.roweatrow.server.model;

import javax.persistence.*;

@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long boat;

    @ManyToOne
    @JoinColumn(name = "shell")
    private Shell shell;

    @ManyToOne
    @JoinColumn(name = "coxswain")
    private Athlete coxswain;
    @ManyToOne
    @JoinColumn(name = "seat_8")
    private Athlete seat_8;
    @ManyToOne
    @JoinColumn(name = "seat_7")
    private Athlete seat_7;
    @ManyToOne
    @JoinColumn(name = "seat_6")
    private Athlete seat_6;
    @ManyToOne
    @JoinColumn(name = "seat_5")
    private Athlete seat_5;
    @ManyToOne
    @JoinColumn(name = "seat_4")
    private Athlete seat_4;
    @ManyToOne
    @JoinColumn(name = "seat_3")
    private Athlete seat_3;
    @ManyToOne
    @JoinColumn(name = "seat_2")
    private Athlete seat_2;
    @ManyToOne
    @JoinColumn(name = "seat_1")
    private Athlete seat_1;

    public Athlete getSeat_8() {
        return seat_8;
    }
}
