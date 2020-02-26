package mk.ukim.finki.mk.consultations.web.servlets;

import mk.ukim.finki.mk.consultations.model.excaptions.InvalidPassword;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;
    private WebContext webContext;

    public LoginServlet(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.webContext = new WebContext(req, resp, req.getServletContext());
        this.springTemplateEngine.process("login.html", this.webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.equals(password)) {
            session.setAttribute("username", username);
            resp.sendRedirect("/");
        }else throw new InvalidPassword();

    }

}
