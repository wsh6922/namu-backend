package com.namu.thenamu.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

        @NotNull(message = "이름은 필수 입력 항목입니다.")
        private String name;

        @NotNull(message = "아이디는 필수 입력 항목입니다.")
        private String userId;

        @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
        private String password;

        private String role;
    }
}
