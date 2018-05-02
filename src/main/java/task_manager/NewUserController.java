package task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.model.User;
import task_manager.model.UserRepository;

@Controller
public class NewUserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/newuser")
    public String newuser(Model model) {
        User user = new User();
        model.addAttribute("newUserForm", user);
        return "newuser";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("newUserForm") User user,
                                   BindingResult result, Model model) {
        userRepository.save(user);
        return "newuser";
    }

}
