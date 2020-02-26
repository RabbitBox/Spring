package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "pizaa-order", urlPatterns = "/pizzaOrder")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private WebContext webContext;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String parametar = req.getParameter("pizza_size");

        Order order = (Order)session.getAttribute("order");
        order.setPizzaSize(parametar);
        session.setAttribute("order", order);

        webContext = new WebContext(req,resp,req.getServletContext());
        resp.setContentType("text/html; charset=UTF-8");
        webContext.setVariable("pizza_size", order.getPizzaSize());
        webContext.setVariable("pizza_type", order.getPizzaType());
        this.springTemplateEngine.process("deliveryInfo.html", this.webContext, resp.getWriter());
    }
}