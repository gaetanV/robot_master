package app.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import app.Services.UserService;
import app.Form.UserRegistration;
import app.Entity.User.User;
import app.Entity.User.UserRepository;

@Controller
@RequestMapping(path = "/api/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public @ResponseBody
    String getAllUsers() {
        List<JSONObject> entities = new ArrayList<JSONObject>();
        List<User> users = userRepository.findAll();
        try {
            users.forEach((user) -> {
                try {
                    JSONObject entity = new JSONObject();
                    entity.put("name", user.getUsername());
                    entity.put("email", user.getEmail());
                    entities.add(entity);
                } catch (JSONException e) {
                    throw new RuntimeException();
                }
            });
        } catch (RuntimeException e) {
            return "{\"response\":\"error\"}";
        }
        return entities.toString();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public @ResponseBody
    String createNewUser(@Valid UserRegistration userRegistration, BindingResult bindingResult) {

        User userExists = userRepository.findByEmail(userRegistration.getEmail());

        JSONObject response = new JSONObject();
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }
        try {
            if (bindingResult.hasErrors()) {
                JSONObject item = new JSONObject();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    item.put(error.getField(), error.getDefaultMessage());
                }
                for (ObjectError objectError : bindingResult.getGlobalErrors()) {
                    item.put("extra", objectError);
                }
                response.put("errors", item);
            } else {
                userService.userRegistration(userRegistration);
                response.put("response", "succes");
            }
            return response.toString();
        } catch (JSONException e) {
            return "{\"response\":\"error\"}";
        }

    }

}