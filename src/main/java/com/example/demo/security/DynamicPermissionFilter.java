package com.example.demo.security;

import com.example.demo.model.EndpointPermission;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class DynamicPermissionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            String path = request.getRequestURI();
            String method = request.getMethod();
            Set<EndpointPermission> permissions = userDetails.getEndpointPermissions();

            boolean allowed = permissions.stream().anyMatch(perm ->
                    (perm.getHttpMethod().equalsIgnoreCase(method) || perm.getHttpMethod().equalsIgnoreCase("ALL")) &&
                    (path.equals(perm.getEndpoint()) || path.matches(perm.getEndpoint().replace("**", ".*")))
            );

            if (!allowed) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden: You do not have permission for this endpoint.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}