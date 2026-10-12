package com.cfs.BookMyShowBE.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Show show; // (1 show --> many booking)

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer; // (m bookings --> done by 1 customer)

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private BigDecimal totalAmount;

    private LocalDateTime bookedAt;

    // whenever you have to use enum
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ElementCollection // if we are not mapping with object, to tell it that our arrayList is String
    @CollectionTable(
            name = "booking_table",
            joinColumns = @JoinColumn(name = "booking_id")
            // 1 (id) --> multiple seats (a1, a2, a3)
    )
    @Column(name = "seat_Label", nullable = false)
    public List<String> seatLabels = new ArrayList<>();

    /*
    Booking entity ke seatLabels ko ek separate table me store karo.
    Us table ka naam booking_table hoga.
    booking_id se original booking se connect karo.
    Har seat label seat_Label column me store hoga.
     */

    public Booking() {

    }

    public Booking(Show show, String customerName, String customerEmail, String customerPhone, BigDecimal totalAmount, List<String> seatLabels) {
        this.show = show;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.totalAmount = totalAmount;

        this.bookedAt = LocalDateTime.now();
        this.seatLabels = new ArrayList<>(seatLabels);
        this.status = BookingStatus.CONFIRMED;
    }

    public Booking(Show show, Customer customer, BigDecimal totalAmount, List<String> seatLabels) {
        this(show, customer.getName(), customer.getEmail(), customer.getPhone(), totalAmount, seatLabels);
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public List<String> getSeatLabels() {
        return seatLabels;
    }

    public void setSeatLabels(List<String> seatLabels) {
        this.seatLabels = seatLabels;
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
    }
}

/*
Movie 1 -------- many Shows
Theatre 1 ------ many Shows
Show 1 --------- many ShowSeats
Show 1 --------- many Bookings
Customer 1 ----- many Bookings
 */

