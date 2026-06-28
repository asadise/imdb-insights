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
@Table(name = "ratings")
public class Rating {
    @Id
    private String tconst;

    @Column(name = "AVERAGERATING")
    private Double averageRating;

    @Column(name = "NUMVOTES")
    private Integer numVotes;
}
