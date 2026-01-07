package com.eternum.book.entity.repository;

import com.eternum.book.entity.repository.id.SceneImageId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "SCENE_IMAGES")
public class SceneImageEntity {

    @EmbeddedId
    private SceneImageId id;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @Column(name = "TIME_OUT")
    private Integer timeOut;
}
