package com.namu.thenamu.utils.annotation;

import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.repository.UserDetail;
import org.springframework.security.authentication.BadCredentialsException;


public class AuthenticationValidator {

    // Principal 객체가 들어오는 것이 아닌 UserDetails의 구현체가 들어오게 된다.
    // Principal은 UserDetails의 참조 인스턴스 객체
    public static Object validatePrincipal(Object principal) {

        if (principal == null) throw new BadCredentialsException("유효하지 않은 사용자 입니다.");

        if ("anonymousUser".equals(principal)) throw new BadCredentialsException("유효하지 않은 사용자 입니다.");

        return principal;
    }

//    public static User validatePrincipal(UserDetail userDetail) {
//
//        if (userDetail == null) throw new BadCredentialsException("유효하지 않은 사용자 입니다.");
//
//        return userDetail.getUser();
//    }
}
