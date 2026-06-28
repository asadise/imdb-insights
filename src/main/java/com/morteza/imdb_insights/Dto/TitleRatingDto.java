package com.morteza.imdb_insights.Dto;

import com.morteza.imdb_insights.entity.Rating;
import com.morteza.imdb_insights.entity.Title;

public record TitleRatingDto(
        Title title,
        Rating rating
) {}
