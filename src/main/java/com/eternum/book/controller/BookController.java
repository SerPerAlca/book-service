package com.eternum.book.controller;

import com.eternum.book.domain.service.SceneService;
import com.eternum.book.entity.response.SceneDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books", headers = "Accept=application/json")
public class BookController {

    protected static final String MAPPING_SCENES = "/scenes/{sceneId}";

    private final SceneService sceneService;

    @GetMapping(value = MAPPING_SCENES, produces = "application/json")
    @ResponseStatus(OK)
    public ResponseEntity<SceneDetailsResponse> getSceneDetailsById(
            @PathVariable("sceneId") final Long sceneId) {

        return ResponseEntity.ok(this.sceneService.getSceneDetailsById(sceneId));
    }
}
