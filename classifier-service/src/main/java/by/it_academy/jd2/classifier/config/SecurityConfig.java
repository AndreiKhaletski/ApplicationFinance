package by.it_academy.jd2.classifier.config;


import by.it_academy.jd2.classifier.controller.filter.JwtFilter;
import by.it_academy.jd2.classifier.controller.http.CategoryController;
import by.it_academy.jd2.classifier.controller.http.CurrencyController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, CurrencyController.REQUEST_MAPPING).permitAll()
                .requestMatchers(HttpMethod.POST, CurrencyController.REQUEST_MAPPING).hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.GET, CategoryController.REQUEST_MAPPING).permitAll()
                .requestMatchers(HttpMethod.POST, CategoryController.REQUEST_MAPPING).hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors().and()
                .addFilterBefore(filter, BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint((request, response, ex) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((request, response, ex) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
