package com.eternum.book.entity.repository;

import com.eternum.book.entity.converter.BooleanNumericConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CHOICES")
public class ChoiceEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", length = 3)
    private Long id;

    @Column(name = "SOURCE_SCENE_ID", nullable = false)
    private Long sourceEsceneId;

    @Column(name = "DESTINATION_SCENE_ID", nullable = false)
    private Long destinationSceneId;

    @Column(name = "CHOICE_TEXT")
    private String choiceText;

    @Column(name = "SORT_ORDER", nullable = false)
    private Integer sortOrder;

    @Column(name = "DESTINATION_TYPE", nullable = false)
    private String destinationType;

    @Column(name = "OBLIGATORY", nullable = false)
    @Convert(converter = BooleanNumericConverter.class)
    private Boolean obligatory;

}
