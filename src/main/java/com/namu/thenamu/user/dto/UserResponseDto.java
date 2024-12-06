package com.namu.thenamu.user.dto;

import com.namu.thenamu.user.domain.User;
import lombok.Builder;

@Builder
public class UserResponseDto {

    private Long id;

    private String userId;

    private String name;

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}
