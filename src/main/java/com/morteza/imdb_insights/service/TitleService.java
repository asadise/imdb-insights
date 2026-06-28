package com.morteza.imdb_insights.service;

import com.morteza.imdb_insights.Dto.BestTitleDto;
import com.morteza.imdb_insights.Dto.TitleRatingDto;
import com.morteza.imdb_insights.entity.Rating;
import com.morteza.imdb_insights.entity.Title;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    private final RatingService ratingService;


    public Page<Title> getAliveDirectorWriterTitles(int page, int size) {
        return titleRepository.findAliveDirectorWriterTitles(PageRequest.of(page, size));
    }

    public Page<BestTitleDto> getBestTitlesPerYear(String genre, Pageable pageable) {
        List<Object[]> rows = titleRepository.findTitlesWithRatings(genre);
        Map<Integer, TitleRatingDto> bestPerYear = rows.stream().map(r ->
                new TitleRatingDto((Title) r[0], (Rating) r[1]))
                .filter(dto -> dto.title().getStartYear() != null)
                .collect(Collectors.toMap(dto -> dto.title().getStartYear(), Function.identity(), this::pickBest));
        List<BestTitleDto> result = bestPerYear.values().stream()
                .sorted(Comparator.comparing(dto -> dto.title().getStartYear()))
                .map(dto -> new BestTitleDto(dto.title().getStartYear(), dto.title().getTconst(), dto.title().getPrimaryTitle(), dto.rating().getAverageRating(), dto.rating().getNumVotes(), dto.title().getGenres()))
                .toList();

        int start = (int) pageable.getOffset();
        if (start >= result.size())
            return new PageImpl<>(List.of(), pageable, result.size());

        int end = Math.min(start + pageable.getPageSize(), result.size());
        return new PageImpl<>(result.subList(start, end), pageable, result.size());
    }

    private TitleRatingDto pickBest(TitleRatingDto left, TitleRatingDto right) {
        return ratingService.score(left.rating()) >= ratingService.score(right.rating()) ? left : right;
    }

}
