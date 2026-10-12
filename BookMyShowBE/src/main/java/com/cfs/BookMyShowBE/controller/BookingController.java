package com.cfs.BookMyShowBE.controller;

import com.cfs.BookMyShowBE.dto.BookingResponse;
import com.cfs.BookMyShowBE.dto.CreateBookingRequest;
import com.cfs.BookMyShowBE.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings") // follows single responsibility principle
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/shows/{showId}")
    // if you want to change status code in postman (201 created)
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse book(@PathVariable Long showId, @RequestBody CreateBookingRequest request)
    {
        return bookingService.book(showId, request);
    }

    @PostMapping("/{bookingId}/cancel") // deleteMapping is not necessary you can use post
    // if you want to change status code in postman (201 created)
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse cancel(@PathVariable Long bookingId, @RequestBody Long profileId)
    {
        return bookingService.cancel(bookingId, profileId);
    }

    @PostMapping("/{bookingId}")
    public BookingResponse find(@PathVariable Long bookingId)
    {
        return bookingService.find(bookingId);
    }
}
