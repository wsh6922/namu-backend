package com.namu.thenamu.utils.mapper;

import com.namu.thenamu.post.domain.Post;
import com.namu.thenamu.post.dto.PostListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "board.id", target = "boardId")
    PostListDto toPostListDto(Post post);

    List<PostListDto> toPostListDto(List<Post> post);
}
