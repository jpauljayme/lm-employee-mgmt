package com.legalmatch.emp_mgmt.logout;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Check if it's an HTMX request
        if ("true".equals(request.getHeader("HX-Request"))) {
            // Use the HX-Redirect response header to redirect the full page
            response.setHeader("HX-Redirect", "/login?logout");
        } else {
            response.sendRedirect("/login?logout");
        }
    }
}
