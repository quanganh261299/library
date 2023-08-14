package com.vti.configuration.security;

import com.vti.configuration.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http, UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService) //truyền user detail
                .passwordEncoder(passwordEncoder);
        // mã hóa mk nhập vào để so sánh với chuỗi mã hóa xem có giống nhau không
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, ErrorHandler handler,
            JwtLoginFilter jwtLoginFilter, JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {
        return http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login",
                                "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**"
                        )
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/books/**")
                        .permitAll()
                        .requestMatchers("/api/v1/books/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/v1/borrow/**")
                        .hasAnyAuthority("ADMIN", "NORMAL_USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/borrow/**")
                        .hasAnyAuthority("ADMIN", "NORMAL_USER")
                        .requestMatchers("/api/v1/borrow/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/accounts/**")
                        .hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer
                        -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(handler)
                        .accessDeniedHandler(handler)
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer
                        -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean //exposed cho FE
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/pages/**", "/auth/**", "/password/**"
                , "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**");
    }
}
