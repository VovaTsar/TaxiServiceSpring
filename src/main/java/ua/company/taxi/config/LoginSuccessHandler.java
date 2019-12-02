package ua.company.taxi.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);

    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (roles.contains("ROLE_ADMIN")) {
            url = "/admin/home";
        } else if (roles.contains("ROLE_USER")) {
            url = "/user/home";
        }

        return url;
    }
}
