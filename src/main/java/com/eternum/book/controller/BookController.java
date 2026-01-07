package com.eternum.book.controller;

import com.eternum.book.domain.service.SceneService;
import com.eternum.book.entity.response.SceneDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books", headers = "Accept=application/json")
public class BookController {

    private final SceneService sceneService;

    @GetMapping("/scenes/{sceneId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SceneDetailsResponse> getSceneDetailsById(
            @PathVariable final Long sceneId) {

        return ok(sceneService.getSceneDetailsById(sceneId));
    }

    @GetMapping("/scenes/chapters/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SceneDetailsResponse>> getFullChapterForParty(
            @PathVariable final Integer chapterId,
            @RequestParam(name = "heroes", required = false) final List<String> heroes) {

        return ok(sceneService.getFullChapterForParty(chapterId, heroes));
    }
}
