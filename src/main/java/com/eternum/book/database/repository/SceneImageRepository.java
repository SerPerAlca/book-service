package com.eternum.book.database.repository;

import com.eternum.book.entity.model.SceneImageDetailModel;
import com.eternum.book.entity.repository.SceneImageEntity;
import com.eternum.book.entity.repository.id.SceneImageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SceneImageRepository extends JpaRepository<SceneImageEntity, SceneImageId> {

    @Query("""
                SELECT new com.eternum.book.entity.model.SceneImageDetailModel(si.id.sceneId, i.path, si.sortOrder, si.timeOut)
                FROM SceneImageEntity si
                JOIN ImageEntity i ON si.id.imageId = i.id
                JOIN SceneEntity s ON si.id.sceneId = s.id
                WHERE s.chapterId = :chapterId
                ORDER BY si.sortOrder ASC
            """)
    List<SceneImageDetailModel> findAllImagesByChapterId(@Param("chapterId") Integer chapterId);

    @Query("""
                SELECT new com.eternum.book.entity.model.SceneImageDetailModel(si.id.sceneId, i.path, si.sortOrder, si.timeOut)
                FROM SceneImageEntity si
                JOIN ImageEntity i ON si.id.imageId = i.id
                JOIN SceneEntity s ON si.id.sceneId = s.id
                WHERE s.id = :sceneId
                ORDER BY si.sortOrder ASC
            """)
    List<SceneImageDetailModel> findAllImagesBySceneId(@Param("sceneId") Long sceneId);
}
