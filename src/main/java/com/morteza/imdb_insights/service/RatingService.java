package com.morteza.imdb_insights.service;

import com.morteza.imdb_insights.Dto.TitleRatingDto;
import com.morteza.imdb_insights.entity.Rating;
import com.morteza.imdb_insights.entity.Title;
import com.morteza.imdb_insights.repository.RatingRepository;
import com.morteza.imdb_insights.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    double score(Rating rating) {
        if (rating == null)
            return 0;

        double avg = rating.getAverageRating() == null ? 0 : rating.getAverageRating();
        int votes = rating.getNumVotes() == null ? 0 : rating.getNumVotes();
        return avg * Math.log(votes + 1);
    }

}
