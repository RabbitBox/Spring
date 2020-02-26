package mk.finki.ukim.mk.lab.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {

    /*
     * We use the service method since it is invoked for all HTTP methods
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/asd");
    }
}
