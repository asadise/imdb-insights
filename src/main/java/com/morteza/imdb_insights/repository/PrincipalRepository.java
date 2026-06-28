package com.morteza.imdb_insights.repository;

import com.morteza.imdb_insights.entity.Principal;
import com.morteza.imdb_insights.entity.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

    @Query("""
                SELECT DISTINCT t
                FROM Title t
                JOIN Principal p1 ON p1.tconst = t.tconst
                JOIN Principal p2 ON p2.tconst = t.tconst
                WHERE p1.nconst = :actor1
                  AND p2.nconst = :actor2
                  AND p1.category IN ('actor', 'actress')
                  AND p2.category IN ('actor', 'actress')
            """)
    Page<Title> findCommonMovies(@Param("actor1") String actor1, @Param("actor2") String actor2, Pageable pageable);

    @Query("""
                SELECT DISTINCT p2.nconst
                FROM Principal p1
                JOIN Principal p2 ON p1.tconst = p2.tconst
                WHERE p1.nconst = :actor
                  AND p1.category IN ('actor','actress')
                  AND p2.category IN ('actor','actress')
                  AND p2.nconst <> :actor
            """)
    Page<String> findCoActors(@Param("actor") String actor, Pageable pageable);

}