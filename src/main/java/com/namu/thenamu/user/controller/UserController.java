package com.namu.thenamu.user.controller;

import com.namu.thenamu.user.dto.UserRequestDto;
import com.namu.thenamu.user.service.UserService;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     * @param signUp
     * @return
     */
    @Transactional
    @PostMapping("/homepage/sign-up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserRequestDto.SignUp signUp) {
        String userId = userService.signUp(signUp);
        return ResponseHandler.responseBuilder(
                HttpStatus.CREATED,
                "회원가입 성공",
                userId
        );
    }

    @GetMapping("/homepage/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                null,
                null
        );
    }
}
