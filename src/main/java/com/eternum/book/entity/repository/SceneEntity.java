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
@Table(name = "SCENES")
public class SceneEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NEXT_SCENE_ID", nullable = false)
    private Long nextSceneId;

    @Column(name = "SCENE_TYPE", nullable = false)
    private String sceneType;

    @Column(name = "CHAPTER_ID", nullable = false)
    private Integer chapterId;

    @Column(name = "SCENE_TEXT", nullable = false)
    private String sceneText;

    @Column(name = "AUDIO_PATH")
    private String audioPath;

    @Column(name = "MUSIC_PATH")
    private String musicPath;

    @Column(name = "SCENE_LOCATION", nullable = false)
    private String sceneLocation;
}
