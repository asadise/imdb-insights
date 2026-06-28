package com.morteza.imdb_insights.repository;

import com.morteza.imdb_insights.entity.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, String> {

    @Query("""
                SELECT DISTINCT t
                FROM Title t
                JOIN Principal d ON d.tconst = t.tconst
                JOIN Principal w ON w.tconst = t.tconst
                JOIN Name n ON n.nconst = d.nconst
                WHERE d.category = 'director'
                  AND w.category = 'writer'
                  AND d.nconst = w.nconst
                  AND n.deathYear = '\\N'
            """)
    Page<Title> findAliveDirectorWriterTitles(Pageable pageable);

    @Query("""
                SELECT t, r
                FROM Title t
                JOIN Rating r ON t.tconst = r.tconst
                WHERE t.genres LIKE %:genre%
            """)
    List<Object[]> findTitlesWithRatings(@Param("genre") String genre);

}