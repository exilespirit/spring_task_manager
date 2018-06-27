package task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.service.EmailServiceImpl;
import task_manager.model.User;
import task_manager.model.UserRepository;
import task_manager.service.SecurityService;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    private SecurityService securityService;

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
        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/user?id="+targetUser.getId();
    }

}
