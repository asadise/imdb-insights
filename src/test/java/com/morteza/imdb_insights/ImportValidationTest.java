package com.morteza.imdb_insights;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ImportValidationTest {

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void shouldLoadTitlesTable() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM titles", Integer.class);

        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    void shouldLoadPrincipalsTable() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM principals", Integer.class);

        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    void shouldLoadNamesTable() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM names", Integer.class);

        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    void shouldLoadRatingTable() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM rating", Integer.class);

        assertNotNull(count);
        assertTrue(count > 0);
    }

}
