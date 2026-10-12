package com.cfs.BookMyShowBE.controller;

import com.cfs.BookMyShowBE.dto.ApiError;
import com.cfs.BookMyShowBE.service.ResourceNotFoundException;
import com.cfs.BookMyShowBE.service.SeatUnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // kahe pr ye custom exception aae toh run this method spring
    ResponseEntity<ApiError> notFound(ResourceNotFoundException exception, HttpServletRequest request)
    {
        return error(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    // remaining exception yaha lapeet dunga
    @ExceptionHandler({SeatUnavailableException.class, IllegalArgumentException.class})
    ResponseEntity<ApiError> conflict(RuntimeException exception, HttpServletRequest request)
    {
        return error(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    ResponseEntity<ApiError> validation(Exception exception, HttpServletRequest request) {
        return error(HttpStatus.BAD_REQUEST, "Request validation failed", request);
    }

    private ResponseEntity<ApiError> error(HttpStatus status, String msg, HttpServletRequest request)
    {
        return ResponseEntity.status(status).body(new ApiError(Instant.now(), status.value(), status.getReasonPhrase(), msg, request.getRequestURI()));
    }
}













/*
- Controller ka kaam API endpoints banana hota hai.

- Booking Flow Example
Suppose user movie book karta hai:
1. User movies dekhta hai
   GET /movies

2. User city ke theatres/shows dekhta hai
   GET /shows?city=Delhi

3. User ek show select karta hai
   GET /shows/{showId}/seats

4. User A1, A2 select karta hai
   POST /shows/{showId}/bookings

5. BookingService:
   - show find karega
   - customer profile find karega
   - selected seats available hain ya nahi check karega
   - seats reserve karega
   - show.availableSeats reduce karega
   - totalAmount = ticketPrice * seats
   - booking save karega

6. API BookingResponse return karegi
 */
