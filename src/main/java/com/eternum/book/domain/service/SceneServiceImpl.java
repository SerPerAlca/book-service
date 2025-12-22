package com.eternum.book.domain.service;

import com.eternum.book.database.repository.SceneRepository;
import com.eternum.book.domain.mapper.SceneResponseMapper;
import com.eternum.book.entity.exception.NotFoundException;
import com.eternum.book.entity.response.SceneDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SceneServiceImpl implements SceneService {

    private final SceneRepository sceneRepository;

    private final SceneResponseMapper sceneResponseMapper;

    @Override
    public SceneDetailsResponse getSceneDetailsById(final Long sceneId) {

        return this.sceneResponseMapper.toSceneResponse(sceneRepository.findById(sceneId)
                .orElseThrow(() -> NotFoundException.create("Scene NOT FOUND")));
    }
}
