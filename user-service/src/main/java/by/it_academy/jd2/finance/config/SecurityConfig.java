package by.it_academy.jd2.finance.config;

import by.it_academy.jd2.finance.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception  {

        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
                )
                .accessDeniedHandler((request, response, ex) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                .and();

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/users/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/security/token/verification").authenticated()
                .requestMatchers("/cabinet/registration").permitAll()
                .requestMatchers("/cabinet/verification").permitAll()
                .requestMatchers("/cabinet/login").permitAll()
                .requestMatchers("/cabinet/me").authenticated()
                .anyRequest().authenticated()
        );

        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}
