package com.eternum.book.database.repository;

import com.eternum.book.entity.model.ChoiceDetailModel;
import com.eternum.book.entity.repository.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Long> {

    @Query("SELECT new com.eternum.book.entity.model.ChoiceDetailModel(" +
            "c.id, c.choiceText, c.destinationSceneId, c.destinationType, c.obligatory, null) " +
            "FROM ChoiceEntity c " +
            "WHERE c.sourceEsceneId = :sceneId")
    List<ChoiceDetailModel> findAllChoicesBySceneId(@Param("sceneId") Long sceneId);
}
