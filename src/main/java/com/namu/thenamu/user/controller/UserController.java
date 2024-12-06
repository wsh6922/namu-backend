package com.namu.thenamu.user.controller;

import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.dto.UserRequestDto;
import com.namu.thenamu.user.dto.UserResponseDto;
import com.namu.thenamu.user.repository.CustomUserDetailService;
import com.namu.thenamu.user.repository.UserDetail;
import com.namu.thenamu.user.service.UserService;
import com.namu.thenamu.utils.response.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final CustomUserDetailService customUserDetailService;

    public UserController(UserService userService, CustomUserDetailService customUserDetailService) {
        this.userService = userService;
        this.customUserDetailService = customUserDetailService;
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

    @GetMapping("/user/session")
    public ResponseEntity<Object> getUserInfo(Principal principal) {
        try {
            log.info("principal: {}", principal.getName());
            UserDetail userDetail = (UserDetail) this.customUserDetailService.loadUserByUsername(principal.getName());
            User user = userDetail.getUser();
            UserResponseDto userResponseDto = UserResponseDto.toUserResponseDto(user);
            log.info("user: {}", user);
            return ResponseHandler.responseBuilder(
                    HttpStatus.OK,
                    "로그인 상태 검증",
                    userResponseDto
            );
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(
                    HttpStatus.OK,
                    "사용자 정보를 로드하는 중 오류가 발생했습니다.",
                    false
            );
        }
    }


    @GetMapping("/homepage/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseHandler.responseBuilder(
                HttpStatus.OK,
                "로그아웃 되었습니다.",
                null
        );
    }
}
