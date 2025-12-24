package com.eternum.book.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDetailsResponse {

    private Long id;
    private String choiceText;
    private Long destinationSceneId;
    private String destinationType;
    private Boolean obligatory;
    private ScenePreviewResponse destinationScene;
}
