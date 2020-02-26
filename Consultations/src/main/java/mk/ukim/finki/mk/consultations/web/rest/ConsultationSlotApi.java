package mk.ukim.finki.mk.consultations.web.rest;

import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.service.ConsultationSlotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value =  "/api/consultations", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ConsultationSlotApi {

    private final ConsultationSlotService consultationSlotService;

    public ConsultationSlotApi(ConsultationSlotService consultationSlotService) {
        this.consultationSlotService = consultationSlotService;
    }


    @GetMapping                                                            // ovaa name mora da e isto so vrednosta na hedero koj so se praka od api.http
    public Page<ConsultationSlot> getAllConsultationSlots(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                                          @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        System.out.println("1");
        return consultationSlotService.getAllConsultationSlots(page, size);
    }

    @GetMapping(params = "term")
    public List<ConsultationSlot> searchConsultationSlots(@RequestParam String term) {
        System.out.println("2");
        return consultationSlotService.searchConsultationSlots(term);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultationSlot createSlot(@RequestHeader String professorId,
                                       @RequestParam("roomName") String roomName,
                                       @RequestParam(value = "dayOfWeek", required = false) Integer dayOfWeek,
                                       @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime from,
                                       @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime to,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder) {

        ConsultationSlot result = consultationSlotService.createSlot(professorId, roomName, DayOfWeek.of(dayOfWeek), date, from, to);
        response.setHeader("Location", builder.path("/api/consultations/{slotId}").buildAndExpand(result.getSlotId()).toUriString());
        System.out.println("3");
        return result;
    }


//    @GetMapping("/{slotId}")
//    public ConsultationSlot getSlot(@PathVariable int slotId) {
//        return this.consultationSlotService.getConsultationSlot(slotId);
//    }



    @PatchMapping("/{slotId}")
    public ConsultationSlot updateSlot(@PathVariable int slotId,
                                       @RequestHeader String professorId,
                                       @RequestParam String roomName,
                                       @RequestParam(value = "dayOfWeek", required = false) Integer dayOfWeek,
                                       @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime from,
                                       @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime to) {
        System.out.println("4");
        return consultationSlotService.updateSlot(slotId, professorId, roomName, DayOfWeek.of(dayOfWeek), date, from, to);
    }

    @DeleteMapping("/{slotId}")
    public void deleteSlot(@PathVariable int slotId) {
        consultationSlotService.deleteSlot(slotId);
    }
}
