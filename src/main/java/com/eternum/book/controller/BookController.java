package com.eternum.book.controller;

import com.eternum.book.domain.service.SceneService;
import com.eternum.book.entity.response.SceneDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books", headers = "Accept=application/json")
public class BookController {

    private final SceneService sceneService;

    @GetMapping("/scenes/{sceneId}")
    @ResponseStatus(HttpStatus.OK)
    public SceneDetailsResponse getSceneDetailsById(
            @PathVariable final Long sceneId) {

        return sceneService.getSceneDetailsById(sceneId);
    }
}
