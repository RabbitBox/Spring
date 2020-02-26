package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "select-pizza", urlPatterns = "/selectPizza")
public class SelectPizza extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private WebContext webContext;

    public SelectPizza(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        String parametar = order.getPizzaType();

        webContext = new WebContext(req,resp,req.getServletContext());
        resp.setContentType("text/html; charset=UTF-8");
        webContext.setVariable("pizzaName",parametar);
        this.springTemplateEngine.process("selectPizzaSize.html", this.webContext, resp.getWriter());
    }
}
