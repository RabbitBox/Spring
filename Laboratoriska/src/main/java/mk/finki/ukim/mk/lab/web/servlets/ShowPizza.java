package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
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

@WebServlet(name = "show-pizza", urlPatterns = "/asd")
public class ShowPizza extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private WebContext webContext;
    private final PizzaService pizzaService;

    public ShowPizza(SpringTemplateEngine springTemplateEngine, PizzaService pizzaService){
        this.springTemplateEngine = springTemplateEngine;
        this.pizzaService = pizzaService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        webContext = new WebContext(req,resp,req.getServletContext());
        List<Pizza> pizzas = pizzaService.listPizzas();
        webContext.setVariable("pizzas",pizzas);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("listPizzas.html", this.webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String parametar = req.getParameter("pizzaName");
        Order order = new Order();
        order.setPizzaType(parametar);
        session.setAttribute("order", order);

        if(parametar == null){
           /* Pizza pizza = new Pizza();
            String parametar1 = req.getParameter("newPizza");
            String parametar2 = req.getParameter("desc")l;

            pizza.setName(parametar1);
            pizza.setDescription(parametar2);

            pizzaService.addPizza(pizza);*/         // ovaj kod e za dodavanje na nova pica ama ima nekoj problem ako stoe tuka, treba poubavo da se razmisle deka ke e

            webContext = new WebContext(req,resp,req.getServletContext());
            webContext.setVariable("error", "Please select pizza!!!");
            List<Pizza> pizzas = pizzaService.listPizzas();
            webContext.setVariable("pizzas",pizzas);
            this.springTemplateEngine.process("listPizzas.html", this.webContext, resp.getWriter());
        }else {
            resp.sendRedirect("/selectPizza");
        }
    }
}
