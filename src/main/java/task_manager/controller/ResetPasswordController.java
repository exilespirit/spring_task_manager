package task_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.validator.PasswordValidator;
import task_manager.model.User;
import task_manager.model.UserRepository;


@Controller
public class ResetPasswordController {

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    PasswordValidator passwordValidator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/resetpassword")
    public String showPasswordPage(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("oldPassField", "password");
        model.addAttribute("newPassError", false);
        model.addAttribute("confirmationError", false);
        //model.addAttribute("oldPassMessage", "");
        //model.addAttribute("confirmationMessage", "");
        User user = new User();
        model.addAttribute("passForm", user);
        log.info("Updating password {}:" + id);
        return "resetpassword";
    }

    @GetMapping("/adminpanel/resetpassword")
    public String showAdminPasswordPage(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        User user = new User();
        model.addAttribute("oldPassField", "hidden");
        model.addAttribute("passForm", user);
        model.addAttribute("newPassError", false);
        model.addAttribute("confirmationError", false);
        //model.addAttribute("oldPassMessage", "");
        //model.addAttribute("confirmationMessage", "");
        log.info("Updating password {}:" + id);
        return "resetpassword";
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public String savePassword(@ModelAttribute("id") String id, @ModelAttribute("passForm") User user,
                               @RequestParam("newpassword") String newPass, @RequestParam("confirmation") String confirmation,
                               BindingResult result, Model model) {
        log.info("Updating password post:" + id);
        passwordValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("oldPassField", "password");
            return "resetpassword";
        }
        User targetUser = userRepository.findById(Long.parseLong(id)).get();
       // if(passwordEncoder.matches(user.getPassword(),targetUser.getPassword())){
            if(newPass.length() >= 5 && newPass.length() <= 32 && confirmation.length() >= 5 && confirmation.length() <= 32) {
                if (newPass.equals(confirmation)) {
                    targetUser.setPassword(passwordEncoder.encode(newPass));
                    userRepository.save(targetUser);
                    log.info("Pass reset for :" + targetUser.getUsername());
                    log.info("Pass :" + newPass);
                    return "redirect:/user?id=" + targetUser.getId();
                } else {
                    log.info("Passwords dont match:" + targetUser.getUsername());
                    model.addAttribute("oldPassField", "password");
                    model.addAttribute("newPassError", true);
                    model.addAttribute("confirmationError", true);
                    model.addAttribute("confirmationMessage", "Passwords don't match.");
                    return "resetpassword";
                }
            } else {
                model.addAttribute("oldPassField", "password");
                model.addAttribute("newPassError", true);
                model.addAttribute("confirmationError", true);
                model.addAttribute("confirmationMessage", "New password should be between 3 and 32 characters.");
                return "resetpassword";
            }
//        } else {
//            log.info("Wrong password :" + targetUser.getUsername());
//            model.addAttribute("oldPassField", "visible");
//            return "resetpassword";
//        }
    }

    @RequestMapping(value = "/adminpanel/resetpassword", method = RequestMethod.POST)
    public String saveAdminPassword(@ModelAttribute("id") String id, @ModelAttribute("passForm") User user,
                               @RequestParam("newpassword") String newPass, @RequestParam("confirmation") String confirmation,
                               BindingResult result, Model model) {
        log.info("Updating password post:" + id);

        User targetUser = userRepository.findById(Long.parseLong(id)).get();
        if(newPass.length()>=5 && newPass.length() <= 32 && confirmation.length() >= 5 && confirmation.length() <= 32) {
            if (newPass.equals(confirmation)) {
                targetUser.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(targetUser);
                log.info("Pass reset for :" + targetUser.getUsername());
                log.info("Pass :" + newPass);
                return "redirect:/adminpanel/update?id=" + targetUser.getId();
            } else {
                log.info("Passwords dont match:" + targetUser.getUsername());
                model.addAttribute("oldPassField", "hidden");
                model.addAttribute("newPassError", true);
                model.addAttribute("confirmationError", true);
                model.addAttribute("confirmationMessage", "Passwords don't match.");
                return "resetpassword";
            }
        } else {
            model.addAttribute("oldPassField", "hidden");
            model.addAttribute("newPassError", true);
            model.addAttribute("confirmationError", true);
            model.addAttribute("confirmationMessage", "New password should be between 3 and 32 characters.");
            return "resetpassword";
        }
    }

    @RequestMapping(value = "resetpassword", method = RequestMethod.POST, params="cancel")
    public String cancelPassword(@ModelAttribute("id") Long id, Model model){
        //Task task = taskRepository.findById(Long.parseLong(id)).get();
        return "redirect:/user?id="+id;
    }

    @RequestMapping(value = "/adminpanel/resetpassword", method = RequestMethod.POST, params="cancel")
    public String cancelAdminPassword(@ModelAttribute("id") Long id, Model model){
        //Task task = taskRepository.findById(Long.parseLong(id)).get();
        return "redirect:/adminpanel/update?id="+id;
    }
}
