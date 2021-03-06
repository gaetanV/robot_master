package app.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;

import app.Services.UserService;
import app.Form.UserRegistration;
import app.Entity.User.User;

@Controller
@RequestMapping(path = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public @ResponseBody
    String getAllUsers() {
        List<JSONObject> entities = new ArrayList<JSONObject>();
        List<User> users = userService.findAll();
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

    @GetMapping(path = "/registration")
    public @ResponseBody
    String createNewUser(@Valid UserRegistration userRegistration, BindingResult bindingResult) {

        if (userService.userMailExist(userRegistration.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }
        try {
            JSONObject response = new JSONObject();
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
