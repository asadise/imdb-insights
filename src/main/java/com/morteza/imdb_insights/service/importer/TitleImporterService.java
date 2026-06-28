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
public class TitleImporterService {

    private final JdbcTemplate jdbc;

    @Value("${imdb.titles}")
    private String titlesPath;

    @PostConstruct
    public void loadTitles() {
        log.info("Starting Titles import from path: {}", titlesPath);
        long start = System.currentTimeMillis();
        try {
            jdbc.execute("""
                    CREATE TABLE IF NOT EXISTS titles AS
                    SELECT *
                    FROM CSVREAD('%s', null, 'fieldSeparator=\t')
                    """.formatted(titlesPath));
            log.info("Titles table created successfully");
            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_titles_tconst
                    ON titles(tconst)
                    """);
            log.info("Index created: idx_titles_tconst");
            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_titles_year
                    ON titles(startYear)
                    """);
            log.info("Index created: idx_titles_year");
            long end = System.currentTimeMillis();
            log.info("Titles import completed successfully in {} ms", (end - start));
        } catch (Exception e) {
            log.error("Failed to import titles from path: {}", titlesPath, e);
            throw e;
        }
    }
}

