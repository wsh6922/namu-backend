package com.namu.thenamu.user.controller;

import com.namu.thenamu.user.dto.UserRequestDto;
import com.namu.thenamu.user.service.UserService;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserRequestDto.SignUp signUp) {
        String userId = userService.signUp(signUp);
        return ResponseHandler.responseBuilder(
                HttpStatus.CREATED,
                "회원가입 성공",
                userId
        );
    }
}
