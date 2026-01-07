package com.eternum.book.domain.service;

import com.eternum.book.database.repository.ChoiceRepository;
import com.eternum.book.database.repository.SceneImageRepository;
import com.eternum.book.database.repository.SceneRepository;
import com.eternum.book.domain.mapper.SceneResponseMapper;
import com.eternum.book.entity.model.ChoiceDetailModel;
import com.eternum.book.entity.model.SceneDetailModel;
import com.eternum.book.entity.model.SceneImageDetailModel;
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
    private final SceneImageRepository sceneImageRepository;
    private final SceneResponseMapper mapper;

    @Override
    public SceneDetailsResponse getSceneDetailsById(final Long sceneId) {

        return this.sceneRepository.getSceneBaseInfo(sceneId)
                .map(scene -> this.buildSceneResponse(sceneId, scene))
                .orElseThrow(() ->
                        new EntityNotFoundException("Scene not found: " + sceneId)
                );
    }

    private SceneDetailsResponse buildSceneResponse(final Long sceneId,
                                                    final SceneDetailModel scene) {

        final List<ChoiceDetailModel> choices = this.choiceRepository.findAllChoicesBySceneId(sceneId);

        final Map<Long, SceneDetailModel> destinationScenes = this.loadDestinationScenes(choices);

        final List<SceneImageDetailModel> images = this.sceneImageRepository.findAllImagesBySceneId(sceneId);

        return this.mapper.toSceneResponse(scene, this.mapChoices(choices, destinationScenes), images);
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
                : this.sceneRepository.findScenePreviewsByIds(destinationIds)
                .stream()
                .collect(Collectors.toMap(
                        SceneDetailModel::getId,
                        identity()
                ));
    }

    @Override
    public List<SceneDetailsResponse> getFullChapterForParty(
            final Integer chapterId,
            final List<String> partyHeroCodes) {

        final List<SceneDetailModel> scenes = this.sceneRepository.findAllScenesByChapterId(chapterId);

        final List<ChoiceDetailModel> choices = this.choiceRepository.findAllVisibleChoicesByChapter(
                chapterId,
                partyHeroCodes
        );

        final List<SceneImageDetailModel> images = this.sceneImageRepository.findAllImagesByChapterId(chapterId);

        final Map<Long, List<ChoiceDetailModel>> choicesBySource = choices.stream()
                .collect(Collectors.groupingBy(ChoiceDetailModel::getSourceSceneId));

        final Map<Long, List<SceneImageDetailModel>> imagesByScene = images.stream()
                .collect(Collectors.groupingBy(SceneImageDetailModel::getSceneId));

        final Map<Long, SceneDetailModel> previewMap = scenes.stream()
                .collect(Collectors.toMap(SceneDetailModel::getId, identity()));

        return scenes.stream()
                .map(scene -> {

                    // Obtenemos las listas agrupadas previamente
                    final List<ChoiceDetailModel> sceneChoices = choicesBySource.getOrDefault(scene.getId(), List.of());
                    final List<SceneImageDetailModel> sceneImages = imagesByScene.getOrDefault(scene.getId(), List.of());

                    final var choiceResponses = this.mapChoices(sceneChoices, previewMap);

                    return this.mapper.toSceneResponse(scene, choiceResponses, sceneImages);
                })
                .toList();
    }

    private List<ChoiceDetailsResponse> mapChoices(final List<ChoiceDetailModel> choices,
                                                   final Map<Long, SceneDetailModel> destinationScenes) {

        return choices.stream()
                .map(choice ->
                        this.mapper.toChoiceResponse(
                                choice,
                                destinationScenes.get(choice.getDestinationSceneId())
                        )
                )
                .toList();
    }

}

