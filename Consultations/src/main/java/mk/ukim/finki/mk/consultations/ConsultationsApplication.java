package mk.ukim.finki.mk.consultations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ConsultationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultationsApplication.class, args);
    }

}
