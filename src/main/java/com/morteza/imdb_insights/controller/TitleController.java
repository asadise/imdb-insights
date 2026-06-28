package com.morteza.imdb_insights.controller;

import com.morteza.imdb_insights.Dto.BestTitleDto;
import com.morteza.imdb_insights.entity.Title;
import com.morteza.imdb_insights.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/titles")
public class TitleController {

    private final TitleService titleService;

    public TitleController(TitleService queryService) {
        this.titleService = queryService;
    }

    @GetMapping("/same-director-writer")
    public Page<Title> getAliveDirectorWriterTitles(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return titleService.getAliveDirectorWriterTitles(page, size);
    }

    @GetMapping("/best-per-year")
    public Page<BestTitleDto> getBestPerYear(@RequestParam String genre,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return titleService.getBestTitlesPerYear(genre, PageRequest.of(page, size));
    }
}
