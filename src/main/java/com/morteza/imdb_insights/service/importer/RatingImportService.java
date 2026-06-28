package com.morteza.imdb_insights.service.importer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingImportService {

    private final JdbcTemplate jdbc;

    @Value("${imdb.rating}")
    private String ratingPath;

    @PostConstruct
    public void loadRating() {
        log.info("Starting Rating import from path: {}", ratingPath);
        long start = System.currentTimeMillis();
        try {
            jdbc.execute("""
                    CREATE TABLE IF NOT EXISTS ratings AS
                    SELECT *
                    FROM CSVREAD('%s', null, 'fieldSeparator=\t')
                    """.formatted(ratingPath));
            log.info("Rating table created successfully");

           jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_ratings_nconst
                    ON ratings(tconst)
                    """);
            log.info("Index created: idx_ratings_tconst");

            long end = System.currentTimeMillis();
            log.info("Rating import completed successfully in {} ms", (end - start));
        } catch (Exception e) {
            log.error("Failed to import rating from path: {}", ratingPath, e);
            throw e;
        }
    }
}