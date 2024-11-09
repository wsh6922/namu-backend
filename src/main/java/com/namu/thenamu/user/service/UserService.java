package com.namu.thenamu.user.service;

import com.namu.thenamu.user.domain.Role;
import com.namu.thenamu.user.domain.User;
import com.namu.thenamu.user.dto.UserRequestDto;
import com.namu.thenamu.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(UserRequestDto.SignUp signUp) {

        validateDuplicateUserId(signUp.getUserId());
        Role roleAssigned = isRoleAssigned(signUp.getRole());

        User user = User.createUser(signUp.getUserId(), signUp.getName(),
                signUp.getPassword(), roleAssigned);

        userRepository.save(user);

        return user.getUserId();
    }

    public void validateDuplicateUserId(String id) {
        if (userRepository.findByUserId(id).isPresent()) throw new IllegalArgumentException("이미 사용중인 아이디 입니다 id: " + id);
    }

    public Role isRoleAssigned(String role) {
        return role != null && !role.isEmpty() ? Role.valueOf(role) : Role.USER;
    }
}
