package com.namu.thenamu.utils.security.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namu.thenamu.user.repository.CustomUserDetailService;
import com.namu.thenamu.utils.security.Handler.LoginFailureHandler;
import com.namu.thenamu.utils.security.Handler.LoginSuccessHandler;
import com.namu.thenamu.utils.security.filter.CustomLoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;

    private final LoginFailureHandler LoginFailureHandler;

    private final CustomUserDetailService customUserDetailService;

    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterAt(customLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class
                )
//                .securityContext(securityContext -> securityContext.securityContextRepository(
//                        new DelegatingSecurityContextRepository(new HttpSessionSecurityContextRepository(),
//                                new RequestAttributeSecurityContextRepository())
//                ))
                .logout(logout -> logout.logoutUrl("/api/homepage/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public CustomLoginAuthenticationFilter customLoginAuthenticationFilter() throws Exception {
        CustomLoginAuthenticationFilter customLoginAuthenticationFilter = new CustomLoginAuthenticationFilter(objectMapper);
        customLoginAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customLoginAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        customLoginAuthenticationFilter.setAuthenticationFailureHandler(LoginFailureHandler);
        return customLoginAuthenticationFilter;
    }
}
