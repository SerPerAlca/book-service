package com.eternum.book.entity.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "SCENE_TYPES")
public class SceneTypeEntity {

    @Id
    @Column(name = "CODE", length = 3)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;
}
