package com.eternum.book.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SceneDetailsResponse {

    private Long id;
    private Long nextSceneId;
    private String sceneType;
    private String chapterDescription;
    private String sceneText;
    private String imagePath;
    private String audioPath;
    private String musicPath;
    private List<ChoiceDetailsResponse> choices;
}
