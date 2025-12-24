package com.eternum.book.domain.service;

import com.eternum.book.database.repository.ChoiceRepository;
import com.eternum.book.database.repository.SceneRepository;
import com.eternum.book.domain.mapper.SceneResponseMapper;
import com.eternum.book.entity.model.ChoiceDetailModel;
import com.eternum.book.entity.model.SceneDetailModel;
import com.eternum.book.entity.response.ChoiceDetailsResponse;
import com.eternum.book.entity.response.SceneDetailsResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eternum.book.domain.constants.Constants.SCENE_TYPE_CODE;
import static java.lang.Boolean.FALSE;
import static java.util.function.Function.identity;

@Service
@RequiredArgsConstructor
public class SceneServiceImpl implements SceneService {

    private final SceneRepository sceneRepository;
    private final ChoiceRepository choiceRepository;
    private final SceneResponseMapper mapper;

    @Override
    public SceneDetailsResponse getSceneDetailsById(final Long sceneId) {

        return sceneRepository.getSceneBaseInfo(sceneId)
                .map(scene -> buildSceneResponse(sceneId, scene))
                .orElseThrow(() ->
                        new EntityNotFoundException("Scene not found: " + sceneId)
                );
    }

    private SceneDetailsResponse buildSceneResponse(final Long sceneId,
                                                    final SceneDetailModel scene) {

        final List<ChoiceDetailModel> choices =
                choiceRepository.findAllChoicesBySceneId(sceneId);

        final Map<Long, SceneDetailModel> destinationScenes =
                loadDestinationScenes(choices);

        return mapper.toSceneResponse(
                scene,
                mapChoices(choices, destinationScenes)
        );
    }

    private Map<Long, SceneDetailModel> loadDestinationScenes(final List<ChoiceDetailModel> choices) {

        final List<Long> destinationIds =
                choices.stream()
                        .filter(c -> FALSE.equals(c.getObligatory()))
                        .filter(c -> SCENE_TYPE_CODE.equals(c.getDestinationType()))
                        .map(ChoiceDetailModel::getDestinationSceneId)
                        .distinct()
                        .toList();

        return destinationIds.isEmpty()
                ? Map.of()
                : sceneRepository.findScenePreviewsByIds(destinationIds)
                .stream()
                .collect(Collectors.toMap(
                        SceneDetailModel::getId,
                        identity()
                ));
    }

    private List<ChoiceDetailsResponse> mapChoices(final List<ChoiceDetailModel> choices,
                                                   final Map<Long, SceneDetailModel> destinationScenes) {

        return choices.stream()
                .map(choice ->
                        mapper.toChoiceResponse(
                                choice,
                                destinationScenes.get(choice.getDestinationSceneId())
                        )
                )
                .toList();
    }

}

