package com.morteza.imdb_insights.repository;

import com.morteza.imdb_insights.entity.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<Name, String> {

}