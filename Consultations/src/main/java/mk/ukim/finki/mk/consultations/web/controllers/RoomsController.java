package mk.ukim.finki.mk.consultations.web.controllers;

import mk.ukim.finki.mk.consultations.model.Building;
import mk.ukim.finki.mk.consultations.model.Room;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidRoomName;
import mk.ukim.finki.mk.consultations.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RoomsController {

    private final RoomService roomService;

    public RoomsController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String showRooms(HttpServletRequest request,     // toa false e poradi toa so na /rooms ne sekogas prakame kveri string
                            @RequestParam(required = false) String query) {
        List<Room> rooms = query == null || query.isEmpty() ?
                this.roomService.getAllRooms() : this.roomService.searchRooms(query);
        Map<Building, List<Room>> buildingRooms = rooms.stream().collect(Collectors.groupingBy(Room::getBuilding));

        request.setAttribute("rooms", rooms);
        request.setAttribute("buildingRooms", buildingRooms);
        request.setAttribute("bodyContent", "rooms"); // ovaa rooms e .html
        request.setAttribute("query", query);
        return "master-template";
    }
    @RequestMapping(value = "/rooms/create", method = RequestMethod.GET)
    public ModelAndView showCreateRoom() {
        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("bodyContent", "create-room");
        return modelAndView;
    }
    @PostMapping("/rooms/create")
    public String createRoom(@RequestParam String name,
                             @RequestParam Building building,
                             @RequestParam String description) {
        this.roomService.createRoom(name, building, description);
        // other ways for redirection: https://www.baeldung.com/spring-redirect-and-forward
        return "redirect:/rooms";
    }
    @GetMapping("/rooms/{name}")
    public ModelAndView showEditRoom(@PathVariable String name) {
        Optional<Room> room = this.roomService.findByName(name);

        ModelAndView modelAndView = new ModelAndView("master-template");
        modelAndView.addObject("bodyContent", "edit-room");
        modelAndView.addObject("room", room.orElseThrow(InvalidRoomName::new));
        return modelAndView;
    }

    @PostMapping("/rooms/{oldName}")
    public String updateRoom(@PathVariable String oldName,
                             @RequestParam String newName,
                             @RequestParam Building building,
                             @RequestParam String description) {
        this.roomService.updateRoom(oldName, newName, building, description);
        return "redirect:/rooms";

    }

    @PostMapping("/rooms/{name}/delete")
    public String deleteRoom(@PathVariable String name) {
        this.roomService.deleteRoom(name);
        return "redirect:/rooms";
    }
}
