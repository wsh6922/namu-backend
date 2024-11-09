//package com.namu.thenamu.user.repository;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        return userRepository.findByUserId(id)
//                .map(user -> UserDetail.builder()
//                        .id(user.getUserId())
//                        .password(user.getPassword())
//                        .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
//                        .build())
//                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 id:" + id));
//    }
//}
