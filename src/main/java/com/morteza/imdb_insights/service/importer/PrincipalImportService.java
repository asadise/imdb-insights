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
public class PrincipalImportService {

    private final JdbcTemplate jdbc;

    @Value("${imdb.principals}")
    private String principalsPath;

    @PostConstruct
    public void loadPrincipals() {
        log.info("Starting Principals import from path: {}", principalsPath);
        long start = System.currentTimeMillis();
        try {
            log.info("Creating principals table using CSVREAD...");
            jdbc.execute("""
                    CREATE TABLE IF NOT EXISTS principals AS
                    SELECT *
                    FROM CSVREAD('%s', null, 'fieldSeparator=\t')
                    """.formatted(principalsPath));
            log.info("Principals table created successfully");

            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_principal_tconst
                    ON principals(tconst)
                    """);
            log.info("Index created: idx_principal_tconst");

            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_principal_nconst
                    ON principals(nconst)
                    """);
            log.info("Index created: idx_principal_nconst");

            jdbc.execute("""
                    CREATE INDEX IF NOT EXISTS idx_principal_category
                    ON principals(category)
                    """);
            log.info("Index created: idx_principal_category");

            long end = System.currentTimeMillis();
            log.info("Principals import completed successfully in {} ms", (end - start));
        } catch (Exception e) {
            log.error("Failed to import principals from path: {}", principalsPath, e);
            throw e;
        }
    }
}