package com.eternum.book.database.repository;

import com.eternum.book.entity.model.ChoiceDetailModel;
import com.eternum.book.entity.repository.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Long> {

    @Query("""
            SELECT new com.eternum.book.entity.model.ChoiceDetailModel(
                c.id,
                c.choiceText,
                c.sourceEsceneId,
                c.destinationSceneId,
                c.destinationType,
                c.obligatory,
                c.heroeCode,
                null)
            FROM ChoiceEntity c
            WHERE c.sourceEsceneId = :sceneId
            """)
    List<ChoiceDetailModel> findAllChoicesBySceneId(@Param("sceneId") Long sceneId);

    @Query("""
                SELECT new com.eternum.book.entity.model.ChoiceDetailModel(
                    c.id,
                    c.choiceText,
                    c.sourceEsceneId,
                    c.destinationSceneId,
                    c.destinationType,
                    c.obligatory,
                    c.heroeCode,
                    null)
                FROM ChoiceEntity c
                JOIN SceneEntity s ON c.sourceEsceneId = s.id
                WHERE s.chapterId = :chapterId
                AND (c.heroeCode IS NULL OR c.heroeCode IN :activeHeroCodes)
                ORDER BY c.sourceEsceneId, c.sortOrder ASC
            """)
    List<ChoiceDetailModel> findAllVisibleChoicesByChapter(
            @Param("chapterId") Integer chapterId,
            @Param("activeHeroCodes") List<String> activeHeroCodes
    );
}
