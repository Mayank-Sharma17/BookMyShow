package com.cfs.BookMyShowBE.service;

import com.cfs.BookMyShowBE.dto.BookingResponse;
import com.cfs.BookMyShowBE.dto.CreateBookingRequest;
import com.cfs.BookMyShowBE.entity.Booking;
import com.cfs.BookMyShowBE.entity.BookingStatus;
import com.cfs.BookMyShowBE.entity.Show;
import com.cfs.BookMyShowBE.entity.ShowSeat;
import com.cfs.BookMyShowBE.repository.BookingRepository;
import com.cfs.BookMyShowBE.repository.CustomerRepository;
import com.cfs.BookMyShowBE.repository.ShowRepository;
import com.cfs.BookMyShowBE.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class BookingService {
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;


    public BookingService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public BookingResponse book(Long showId, CreateBookingRequest request) {
        // shows
        Show show = showRepository.findById(showId).orElseThrow(()->new ResourceNotFoundException("Show not found"));

        // java 11, profile
        var customer = customerRepository.findById(request.profileId()).orElseThrow(()->new ResourceNotFoundException("Profile not found"));

        // List of Labels
        List<String> labels = request.seatLabels().stream()
                .map(label -> label.trim().toUpperCase(Locale.ROOT)).toList();

        if(labels.stream().distinct().count() != labels.size()) {
            throw new SeatUnavailableException("Duplicate seat labels are not allowed");
        }
        List<ShowSeat> seats = showSeatRepository.findForUpdate(showId, labels); // status changed
        if(seats.size() != labels.size() || seats.stream().anyMatch(ShowSeat::isReserved)) {
            throw new SeatUnavailableException("One or more selected seats are unavailable");
        }
        seats.forEach(ShowSeat::reserve); // booking status changed --> state change -> proxy --> use transactional annotation
        show.reserve(labels.size());

        BigDecimal total = show.getTicketPrice().multiply(BigDecimal.valueOf(labels.size()));
        Booking booking = bookingRepository.save(new Booking(show, customer, total, labels));

        return BookingResponse.from(booking); // Booking object ko BookingResponse DTO mein convert karke return karo.
    }

    @Transactional
    public BookingResponse find(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return BookingResponse.from(booking);
    }

    public List<BookingResponse> findByProfileId(Long profileId) {
        if(!customerRepository.existsById(profileId)) {
            throw new ResourceNotFoundException("Profile not found");
        }

        return bookingRepository.findByCustomerIdOrderByBookedAtDesc(profileId).stream()
                .map(BookingResponse::from).toList(); // first it convert it int Stream<Booking> --> map on each booking and call form method for each object using method reference operator --> converting back to List<Booking>
    }

    @Transactional
    public BookingResponse cancel(Long bookingId, Long profileId) {
        Booking booking = bookingRepository.findByIdAndCustomerId(bookingId, profileId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if(booking.getStatus() == BookingStatus.CONFIRMED) {
            List<ShowSeat> seats = showSeatRepository.findForUpdate(booking.getShow().getId(), booking.getSeatLabels());
            seats.forEach(ShowSeat::release);
            booking.getShow().release(booking.getSeatLabels().size());
            booking.cancel();
        }

        return BookingResponse.from(booking);
    }
}





// actual logic written here

// That's why we used wrapper classes like BigDecimal we can use multiply() method, otherwise we have to write it by ourself

// In Java 8, the method reference operator (::) is a shorthand notation used to refer to an existing method without invoking it. It acts as a more readable and compact alternative to a lambda expression when that lambda does nothing but forward its arguments directly to an existing method.

// @Transactional annotation --> one is from Springframework and another is from jakarta, so you have to use jakarta one because JPA will handle out transaction --> atomicity --> yaa seat book hoo ya toh naa toh (proxy) lagane hai book() pr

// Optional Java ka ek container/wrapper class hai jo represent karta hai:
//"Value hai bhi sakti hai, nahi bhi."
//Ye mainly null ko safely handle karne ke liye use hota hai.