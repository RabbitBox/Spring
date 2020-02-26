package mk.ukim.finki.mk.consultations.web.servlets;

import mk.ukim.finki.mk.consultations.model.Room;
import mk.ukim.finki.mk.consultations.service.RoomService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Profile("servlet")
@WebServlet(name = "room123", urlPatterns = "/rooms")
public class RoomThymeleafServlet extends HttpServlet {

    private RoomService roomService;
    private SpringTemplateEngine springTemplateEngine;
    private WebContext webContext;

    public RoomThymeleafServlet(SpringTemplateEngine springTemplateEngine, RoomService roomService) {
        this.springTemplateEngine = springTemplateEngine;
        this.roomService = roomService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        webContext = new WebContext(req,resp,req.getServletContext());
        List<Room> rooms = roomService.getAllRooms();
        webContext.setVariable("rooms",rooms);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("rooms.html", this.webContext, resp.getWriter());
    }
}