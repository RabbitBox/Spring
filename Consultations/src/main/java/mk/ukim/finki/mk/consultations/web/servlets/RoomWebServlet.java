package mk.ukim.finki.mk.consultations.web.servlets;

import mk.ukim.finki.mk.consultations.model.Room;
import mk.ukim.finki.mk.consultations.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="room-web-servlet",urlPatterns = "/servlet/room")
public class RoomWebServlet extends HttpServlet {

    private RoomService roomService;

    public RoomWebServlet(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ipAddress = req.getRemoteHost();
        String clientAgent = req.getHeader("User-Agent");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        List<Room> rooms = roomService.getAllRooms();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>");
        writer.write("Hi "+clientAgent);
        writer.write("</h1>");
        writer.write("<div>");
        writer.write("Your IP address is:");
        writer.write(ipAddress);
        writer.write("</div>");
        writer.write("<div>Let's list the rooms</div>");
        writer.write("<ul>");
        rooms.stream()
                .forEach(r->
                        writer.write("<li>"+r.getName()+"</li>")
                );
        writer.write("</ul>");
        writer.write("</body>");
        writer.write("</html>");
    }
}