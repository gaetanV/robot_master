package app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import app.Collection.Cards.Cards;

@Controller
@RequestMapping(path = "/api/card/")
public class CardController {

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/")
    public @ResponseBody
    String cardInit() {
        Cards cards = new Cards();
        return cards.getCards1ToString();
    }

}
