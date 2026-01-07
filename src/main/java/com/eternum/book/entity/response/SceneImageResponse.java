package com.eternum.book.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SceneImageResponse {

    private String path;
    private Integer sortOrder;
    private Integer timeOut;

}
