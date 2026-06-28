package com.morteza.imdb_insights.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "names")
public class Name {

    @Id
    private String nconst;

    @Column(name = "PRIMARYNAME")
    private String primaryName;

    @Column(name = "DEATHYEAR")
    private String deathYear;
}