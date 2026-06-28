package com.morteza.imdb_insights.controller;

import com.morteza.imdb_insights.entity.Title;
import com.morteza.imdb_insights.service.PrincipalService;
import com.morteza.imdb_insights.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/principals")
public class PrincipalController {

    private final PrincipalService principalService;

    public PrincipalController(PrincipalService principalService) {
        this.principalService = principalService;
    }

    @GetMapping("/common-movies")
    public Page<Title> getCommonMovies(@RequestParam String actor1,
                           @RequestParam String actor2,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        return principalService.getCommonMovies(actor1, actor2, page, size);
    }

    @GetMapping("/co-actors")
    public Page<String> getCoActors(@RequestParam String actor,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return principalService.getCoActors(actor, page, size);
    }

}
