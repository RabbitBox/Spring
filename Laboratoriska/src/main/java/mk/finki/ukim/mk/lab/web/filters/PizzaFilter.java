package mk.finki.ukim.mk.lab.web.filters;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("servlet")
@WebFilter
public class PizzaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        Order order = (Order) httpRequest.getSession().getAttribute("order");

        String path = httpRequest.getServletPath();

        if (!"/asd".equals(path) && (order == null || order.getPizzaType() == null)) {
            httpResp.sendRedirect("/asd");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}