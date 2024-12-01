package com.namu.thenamu.utils.annotation;

import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.repository.UserDetail;
import org.springframework.security.authentication.BadCredentialsException;

public class AuthenticationValidator {

    public static User validatePrincipal(UserDetail userDetail) {

        if (userDetail == null) throw new BadCredentialsException("유효하지 않은 사용자 입니다.");

        if ("anonymousUser".equals(userDetail.getUsername())) throw new BadCredentialsException("유효하지 않은 사용자 입니다.");

        return userDetail.getUser();
    }
}
