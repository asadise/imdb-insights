package com.morteza.imdb_insights.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "principals")
public class Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TCONST")
    private String tconst;

    @Column(name = "NCONST")
    private String nconst;

    @Column(name = "CATEGORY")
    private String category;
}