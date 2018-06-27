package task_manager;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import task_manager.model.UserRepository;

@Component("customAuthenticationSuccessHandler")
public class RedirectHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(request, response,"/adminpanel");

            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                String username = authentication.getName();
                String redirectUrl = "/user?id=" + userRepository.findByUsername(username).getId();
                redirectStrategy.sendRedirect(request, response, redirectUrl);

            } else if (grantedAuthority.getAuthority().equals("ROLE_BLOCKED")) {
                redirectStrategy.sendRedirect(request, response, "/blocked");
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
