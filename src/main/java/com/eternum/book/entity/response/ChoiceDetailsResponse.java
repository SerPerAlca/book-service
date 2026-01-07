package com.eternum.book.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDetailsResponse {

    private Long choiceId;
    private String choiceText;
    private Long sourceSceneId;
    private Long destinationSceneId;
    private String destinationType;
    private Boolean obligatory;
    private String heroeCode;
    private ScenePreviewResponse destinationScene;
}
