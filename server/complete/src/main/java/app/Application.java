package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import cards.Cards;


@SpringBootApplication
@RestController
public class Application {
    
    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public String home() {
        Cards cards = new Cards();
        return cards.getCards1ToString();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
