package com.eternum.book.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SceneDetailModel {

    private Long id;
    private Long nextSceneId;
    private String sceneType;
    private String chapterDescription;
    private String sceneText;
    private String audioPath;
    private String musicPath;
    private String sceneLocation;
    private List<ChoiceDetailModel> choices;

    // Constructor para JPQL
    public SceneDetailModel(final Long id, final Long nextSceneId, final String sceneType, final String chapterDescription,
                            final String sceneText, final String audioPath, final String musicPath, final String sceneLocation) {
        this.id = id;
        this.nextSceneId = nextSceneId;
        this.sceneType = sceneType;
        this.chapterDescription = chapterDescription;
        this.sceneText = sceneText;
        this.audioPath = audioPath;
        this.musicPath = musicPath;
        this.sceneLocation = sceneLocation;
    }
}
