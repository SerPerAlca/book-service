package com.eternum.book.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenePreviewResponse {

    private Long id;
    private String sceneText;
    private String imagePath;
    
}