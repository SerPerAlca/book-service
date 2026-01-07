package com.eternum.book.entity.repository.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SceneImageId {

    @Column(name = "SCENE_ID")
    private Long sceneId;

    @Column(name = "IMAGE_ID")
    private Long imageId;
}
