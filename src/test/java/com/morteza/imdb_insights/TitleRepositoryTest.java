package com.morteza.imdb_insights;

import com.morteza.imdb_insights.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class TitleRepositoryTest {

    @Autowired
    TitleRepository titleRepository;

    @Test
    void shouldFindGenresByActor() {
        var result = titleRepository.findGenresByActor("nm0644728");

        assertNotNull(result);
    }
}
