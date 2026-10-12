package com.cfs.BookMyShowBE.entity;

import jakarta.persistence.*;

@Entity
// candidate key dede
@Table(name = "show_seats", uniqueConstraints = @UniqueConstraint(name = "uk_show_seat", columnNames = {"show_id", "seatLabel"}))
// individual columns duplicate hosakte hai but show_id+seatLabel combine column duplicate nahe hosakte hai
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // (Many show seats --> 1 show)
    private Show show;

    private String seatLabel; // snake_case --> DB col name -> seat_label

    private boolean reserved;

    public ShowSeat() {
    }

    public ShowSeat(Show show, String seatLabel) {
        this.show = show;
        this.seatLabel = seatLabel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatLabel() {
        return seatLabel;
    }

    public boolean isReserved() {
        return reserved;
    }

    // Reserve or release the seat
    public void reserve() {
        reserved = true;
    }

    public void release() {
        reserved = false;
    }
}
