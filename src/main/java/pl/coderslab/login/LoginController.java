package pl.coderslab.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

    @GetMapping(value = {""})
    public String home() {
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String login() {
            return "login/login";
    }

    @GetMapping(value = {"/login/{error}"})
    public String loginError(@PathVariable String error, Model model) {
        model.addAttribute("error", error);
        return "login/login";
    }

}
