package task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.model.User;
import task_manager.model.UserRepository;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        User user = new User();
        model.addAttribute("loginForm", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") User user,
                                   BindingResult result, Model model) {
        User targetUser = userRepository.findByUsername(user.getUsername());
        return "redirect:/user?id="+targetUser.getId();
    }

}
