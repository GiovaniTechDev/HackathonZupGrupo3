package com.grupo3.app.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AppSecurityConfig {

    private final UserDetailsService userDetailsService;

    // @formatter:off
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/", "/resources/**", "/static/**", "/assets/**", "/css/**", "/js/**",
                                     "/vendors/**", "/bootstrap/**").permitAll()
                    .requestMatchers("/home").permitAll()
                    .requestMatchers("/registro").permitAll()
                    .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                    .loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error").permitAll())
                .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
				.rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret").tokenValiditySeconds(86400));
                    //.logoutSuccessUrl("/login?logout").permitAll());
                //.sessionManagement(sessionManagement -> sessionManagement.invalidSessionUrl("/login?logout"));
                //.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403"));

        return http.build();
    }
    // @formatter:on

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
