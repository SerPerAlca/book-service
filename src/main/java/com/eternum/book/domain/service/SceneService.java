package com.eternum.book.domain.service;

import com.eternum.book.entity.response.SceneDetailsResponse;

public interface SceneService {

    SceneDetailsResponse getSceneDetailsById(final Long sceneId);
}
