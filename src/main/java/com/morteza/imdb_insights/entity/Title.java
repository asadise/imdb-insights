package com.morteza.imdb_insights.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "titles")
public class Title {

    @Id
    private String tconst;

    @Column(name = "PRIMARYTITLE")
    private String primaryTitle;

    @Column(name = "STARTYEAR")
    private Integer startYear;

    @Column(name = "GENRES")
    private String genres;
}