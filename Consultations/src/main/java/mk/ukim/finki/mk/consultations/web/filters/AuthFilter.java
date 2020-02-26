package mk.ukim.finki.mk.consultations.web.filters;

import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("servlet")   // vaka e neaktiviran, ako sakam da go aktiviram vo app prop. treba spring.profile.active=servlet

@WebFilter
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;


        String name = (String) httpRequest
                .getSession()
                .getAttribute("username");
        String path = httpRequest.getServletPath();

        if (!"/login".equals(path) && (name == null || name.isEmpty())) {
            httpResp.sendRedirect("/login");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}