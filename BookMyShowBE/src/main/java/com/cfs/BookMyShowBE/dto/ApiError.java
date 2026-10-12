package com.cfs.BookMyShowBE.dto;

import java.time.Instant;

public record ApiError(Instant timestamp, int status, String error, String message, String path) {
}

/*
 - DTO ka kaam hota hai API input/output ko clean rakhna. Entity directly expose nahi karte.
 */
