package com.cfs.BookMyShowBE.dto;

import com.cfs.BookMyShowBE.entity.Movie;

public record MovieResponse(
        Long id, String title, String language, String genre, Integer durationMinutes,
        String certificate, String description, String posterUrl, String trailerUrl) {

    // yaha ham movie entity as a object le rahe hai usee ham uske information le rahe hai or via constructor return kar rahe hai ek object(bean) as a response
    public static MovieResponse from (Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getLanguage(),
                movie.getGenre(),
                movie.getDurationMinutes(),
                movie.getCertificate(),
                movie.getDescription(),
                movie.getPosterUrl(),
                movie.getTrailerUrl()
        );
    }
}
