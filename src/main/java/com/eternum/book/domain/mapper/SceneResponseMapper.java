package com.eternum.book.domain.mapper;

import com.eternum.book.entity.repository.SceneEntity;
import com.eternum.book.entity.response.SceneDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SceneResponseMapper {

    SceneDetailsResponse toSceneResponse(final SceneEntity source);
}
