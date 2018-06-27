package task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.model.User;
import task_manager.model.UserRepository;

import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/adminpanel")
    public String showAdminPanel(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("users", list);

        return "adminpanel";
    }

    @RequestMapping(value = "/adminpanel", method = RequestMethod.POST, params="block")
    public String blockUser(@RequestParam(name="id", required=true) String id, Model model) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        if (user.getRole().equals("ROLE_BLOCKED")) {
            user.setRole("ROLE_USER");
        } else {
            user.setRole("ROLE_BLOCKED");
        }
        userRepository.save(user);
        return "redirect:/adminpanel";
    }
}
