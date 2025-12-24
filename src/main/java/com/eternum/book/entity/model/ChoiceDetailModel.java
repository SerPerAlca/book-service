package com.eternum.book.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDetailModel {

    private Long id;
    private String choiceText;
    private Long destinationSceneId;
    private String destinationType;
    private Boolean obligatory;
    private SceneDetailModel destinationScene;
}
