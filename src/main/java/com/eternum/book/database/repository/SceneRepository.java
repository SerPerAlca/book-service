package com.eternum.book.database.repository;

import com.eternum.book.entity.model.SceneDetailModel;
import com.eternum.book.entity.repository.SceneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SceneRepository extends JpaRepository<SceneEntity, Long> {

    @Query("""
                SELECT new com.eternum.book.entity.model.SceneDetailModel(
                    s.id, s.nextSceneId, s.sceneType, chap.description,
                    s.sceneText, s.imagePath, s.audioPath, s.musicPath
                )
                FROM SceneEntity s
                JOIN ChapterEntity chap ON s.chapterId = chap.id
                WHERE s.id = :sceneId
            """)
    Optional<SceneDetailModel> getSceneBaseInfo(@Param("sceneId") Long sceneId);

    @Query("""
                SELECT new com.eternum.book.entity.model.SceneDetailModel(
                    s.id, null, null, null,
                    s.sceneText, s.imagePath, null, null
                )
                FROM SceneEntity s
                WHERE s.id IN :ids
            """)
    List<SceneDetailModel> findScenePreviewsByIds(@Param("ids") List<Long> ids);

}
