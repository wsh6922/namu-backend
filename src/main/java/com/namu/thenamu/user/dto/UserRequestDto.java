package com.namu.thenamu.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequestDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUp {

        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        private String name;

        @NotBlank(message = "아이디는 필수 입력 항목입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        private String password;

        private String role;
    }
}
