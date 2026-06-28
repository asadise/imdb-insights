package com.morteza.imdb_insights.service;

import com.morteza.imdb_insights.entity.Title;
import com.morteza.imdb_insights.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalService {
    private final PrincipalRepository principalRepository;

    public Page<Title> getCommonMovies(String actor1, String actor2, int page, int size) {
        return principalRepository.findCommonMovies(actor1, actor2, PageRequest.of(page, size));
    }

    public Page<String> getCoActors(String actor, int page, int size) {
        return principalRepository.findCoActors(actor, PageRequest.of(page, size));
    }
}
