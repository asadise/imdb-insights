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
public class NameImportService {

    private final JdbcTemplate jdbc;

    @Value("${imdb.names}")
    private String namesPath;

    @PostConstruct
    public void loadNames() {
        log.info("Starting Names import from path: {}", namesPath);
        long start = System.currentTimeMillis();
        try {
            jdbc.execute("""
                    CREATE TABLE IF NOT EXISTS names AS
                    SELECT *
                    FROM CSVREAD('%s', null, 'fieldSeparator=\t')
                    """.formatted(namesPath));
            log.info("Names table created successfully");

            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_names_nconst
                    ON names(nconst)
                    """);
            log.info("Index created: idx_names_nconst");

            long end = System.currentTimeMillis();
            log.info("Names import completed successfully in {} ms", (end - start));
        } catch (Exception e) {
            log.error("Failed to import names from path: {}", namesPath, e);
            throw e;
        }
    }
}