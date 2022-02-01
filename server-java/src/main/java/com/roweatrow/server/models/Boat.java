package com.roweatrow.server.models;

import javax.persistence.*;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @Column(name = "boat")
    private Long boat;

    @Column(name = "shell")
    private Long shell;

    @Column(name = "coxswain")
    private Long coxswain;

    @Column(name = "seat_8")
    private Long seat8;

    @Column(name = "seat_7")
    private Long seat7;

    @Column(name = "seat_6")
    private Long seat6;

    @Column(name = "seat_5")
    private Long seat5;

    @Column(name = "seat_4")
    private Long seat4;

    @Column(name = "seat_3")
    private Long seat3;

    @Column(name = "seat_2")
    private Long seat2;

    @Column(name = "seat_1")
    private Long seat1;

    public Long getBoat() {
        return this.boat;
    }

    public void setBoat(Long boat) {
        this.boat = boat;
    }

    public Long getShell() {
        return this.shell;
    }

    public void setShell(Long shell) {
        this.shell = shell;
    }

    public Long getCoxswain() {
        return this.coxswain;
    }

    public void setCoxswain(Long coxswain) {
        this.coxswain = coxswain;
    }

    public Long getSeat8() {
        return this.seat8;
    }

    public void setSeat8(Long seat8) {
        this.seat8 = seat8;
    }

    public Long getSeat7() {
        return this.seat7;
    }

    public void setSeat7(Long seat7) {
        this.seat7 = seat7;
    }

    public Long getSeat6() {
        return this.seat6;
    }

    public void setSeat6(Long seat6) {
        this.seat6 = seat6;
    }

    public Long getSeat5() {
        return this.seat5;
    }

    public void setSeat5(Long seat5) {
        this.seat5 = seat5;
    }

    public Long getSeat4() {
        return this.seat4;
    }

    public void setSeat4(Long seat4) {
        this.seat4 = seat4;
    }

    public Long getSeat3() {
        return this.seat3;
    }

    public void setSeat3(Long seat3) {
        this.seat3 = seat3;
    }

    public Long getSeat2() {
        return this.seat2;
    }

    public void setSeat2(Long seat2) {
        this.seat2 = seat2;
    }

    public Long getSeat1() {
        return this.seat1;
    }

    public void setSeat1(Long seat1) {
        this.seat1 = seat1;
    }
}
