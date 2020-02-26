package mk.ukim.finki.mk.consultations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Building {
    MF("Простории за држење настава во зградата на МФ и ФЕИТ"),
    TMF("Простории за држење на настава на ТМФ"),
    B("Бараки - простории на западната страна на кампусот"),
    LAB("Компјутерски училници");

    private String description;

}
