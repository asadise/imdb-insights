package com.morteza.imdb_insights.Dto;

public record BestTitleDto(
        Integer year,
        String tconst,
        String title,
        Double rating,
        Integer votes,
        String genres) {
}
