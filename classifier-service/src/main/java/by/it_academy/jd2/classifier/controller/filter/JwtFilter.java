package by.it_academy.jd2.classifier.controller.filter;


import by.it_academy.jd2.classifier.controller.http.CategoryController;
import by.it_academy.jd2.classifier.controller.http.CurrencyController;
import by.it_academy.jd2.classifier.service.detailesservice.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final Set<String> GET_NOT_ALLOWED_URLS = Set.of(
            CurrencyController.REQUEST_MAPPING,
            CategoryController.REQUEST_MAPPING);

    private final HttpSecurityClient httpSecurityClient;

    public JwtFilter(HttpSecurityClient httpSecurityClient) {
        this.httpSecurityClient = httpSecurityClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        CustomUserDetails customUserDetails = httpSecurityClient.verifyToken(request);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                customUserDetails,
                null,
                customUserDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if (HttpMethod.GET.name().equals(method) && GET_NOT_ALLOWED_URLS.contains(requestURI)) {
            return true;
        }

        return false;
    }
}

