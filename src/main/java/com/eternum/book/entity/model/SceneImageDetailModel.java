package com.eternum.book.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SceneImageDetailModel {

    private Long sceneId;
    private String path;
    private Integer sortOrder;
    private Integer timeOut;

}
