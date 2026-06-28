package com.morteza.imdb_insights;

import com.morteza.imdb_insights.repository.PrincipalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PrincipalRepositoryTest {

    @Autowired
    PrincipalRepository principalRepository;

    @Test
    void shouldFindMoviesByActor() {
        var result = principalRepository.findCoActors(
                "nm0001908",
                PageRequest.of(0, 10)
        );

        assertNotNull(result);
        assertTrue(result.getTotalElements() >= 0);
    }
}
