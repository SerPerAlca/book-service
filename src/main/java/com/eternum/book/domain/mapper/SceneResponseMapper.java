package com.eternum.book.domain.mapper;

import com.eternum.book.entity.model.ChoiceDetailModel;
import com.eternum.book.entity.model.SceneDetailModel;
import com.eternum.book.entity.response.ChoiceDetailsResponse;
import com.eternum.book.entity.response.SceneDetailsResponse;
import com.eternum.book.entity.response.ScenePreviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SceneResponseMapper {

    @Mapping(target = "choices", source = "choices")
    SceneDetailsResponse toSceneResponse(final SceneDetailModel scene, final List<ChoiceDetailsResponse> choices);


    @Mapping(target = "id", source = "choice.id")
    @Mapping(target = "choiceText", source = "choice.choiceText")
    @Mapping(target = "destinationSceneId", source = "choice.destinationSceneId")
    @Mapping(target = "destinationType", source = "choice.destinationType")
    @Mapping(target = "obligatory", source = "choice.obligatory")
    @Mapping(target = "destinationScene", source = "destinationScene", qualifiedByName = "toScenePreview")
    ChoiceDetailsResponse toChoiceResponse(final ChoiceDetailModel choice, final SceneDetailModel destinationScene);

    @Named("toScenePreview")
    default ScenePreviewResponse toScenePreview(final SceneDetailModel model) {
        return model == null
                ? null
                : new ScenePreviewResponse(
                model.getId(),
                model.getSceneText(),
                model.getImagePath()
        );
    }
}
