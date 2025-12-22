package com.eternum.book.entity.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CHAPTERS")
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", length = 3)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
