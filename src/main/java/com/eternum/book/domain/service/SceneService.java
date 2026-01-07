package com.eternum.book.domain.service;

import com.eternum.book.entity.response.SceneDetailsResponse;

import java.util.List;

public interface SceneService {

    SceneDetailsResponse getSceneDetailsById(final Long sceneId);

    List<SceneDetailsResponse> getFullChapterForParty(
            Integer chapterId,
            List<String> partyHeroCodes);
}
