package task_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.validator.NewUserValidator;
import task_manager.validator.UserValidator;
import task_manager.model.User;
import task_manager.model.UserRepository;
import task_manager.service.NotificationService;

import java.util.List;

@Controller
public class NewUserController {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private NewUserValidator newUserValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/newuser")
    public String newuser(Model model) {
        User user = new User();
        model.addAttribute("newUserForm", user);
        model.addAttribute("delButton", "hidden");
        model.addAttribute("blockButton", "hidden");
        model.addAttribute("usernameField", "text");
        return "newuser";
    }

    @GetMapping("/user/update")
    public String updateUser(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("delButton", "visible");
        model.addAttribute("blockButton", "hidden");
        model.addAttribute("usernameField", "hidden");
        User user = userRepository.findById(Long.parseLong(id)).get();
        model.addAttribute("newUserForm", user);

        return "newuser";
    }

    @GetMapping("/adminpanel/update")
    public String updateUserAdm(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("delButton", "visible");
        model.addAttribute("blockButton", "visible");
        model.addAttribute("usernameField", "hidden");
        User user = userRepository.findById(Long.parseLong(id)).get();

        model.addAttribute("newUserForm", user);
        if (user.getRole().equals("ROLE_BLOCKED")) {
            model.addAttribute("isBlocked","Unblock");
        } else {
            model.addAttribute("isBlocked","Block");
        }

        return "newuser";
    }


    @RequestMapping(value = "/newuser", method = RequestMethod.POST, params="save")
    public String saveNewUser(@ModelAttribute("newUserForm") User user,
                           BindingResult result, Model model) {
        userValidator.validate(user, result);
        newUserValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("delButton", "hidden");
            model.addAttribute("blockButton", "hidden");
            model.addAttribute("usernameField", "text");
            return "newuser";
        }

        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user?id="+user.getId();
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, params="save")
    public String saveUser(@ModelAttribute("newUserForm") User user,
                           BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("delButton", "visible");
            model.addAttribute("blockButton", "hidden");
            model.addAttribute("usernameField", "hidden");
            return "newuser";
        }

        user.setRole("ROLE_USER");
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user?id="+user.getId();
    }

    @RequestMapping(value = "/adminpanel/update", method = RequestMethod.POST, params="save")
    public String saveUserAdm(@ModelAttribute("newUserForm") User user,
                              BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("delButton", "visible");
            model.addAttribute("blockButton", "visible");
            model.addAttribute("usernameField", "hidden");
            return "newuser";
        }
        user.setRole(userRepository.findByUsername(user.getUsername()).getRole());

        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/adminpanel";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST, params="cancel")
    public String cancelNewUser(@ModelAttribute("newUserForm") User user,
                           BindingResult result, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, params="cancel")
    public String cancelUser(@ModelAttribute("newUserForm") User user,
                             BindingResult result, Model model) {
        return "redirect:/user?id="+user.getId();
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, params="delete")
    public String deleteUser(@ModelAttribute("newUserForm") User user,
                             BindingResult result, Model model) {
        userRepository.delete(user);
        return "redirect:/";
    }



    @RequestMapping(value = "/adminpanel/update", method = RequestMethod.POST, params="delete")
    public String deleteUserAdm(@ModelAttribute("newUserForm") User user,
                             BindingResult result, Model model) {
        userRepository.delete(user);
        return "redirect:/adminpanel";
    }

    @RequestMapping(value = "/adminpanel/update", method = RequestMethod.POST, params="cancel")
    public String cancelUserAdm(@ModelAttribute("newUserForm") User user,
                                BindingResult result, Model model) {
        return "redirect:/adminpanel";
    }

    @RequestMapping(value = "/adminpanel/update", method = RequestMethod.POST, params="block")
    public String blockUserAdm(@ModelAttribute("newUserForm") User user,
                                   BindingResult result, Model model) {
        User targetUser = userRepository.findById(user.getId()).get();
        if (targetUser.getRole().equals("ROLE_BLOCKED")) {
            user.setRole("ROLE_USER");
        } else {
            user.setRole("ROLE_BLOCKED");
            log.info("Start blocking of:" + user.getId());
            List<Object> loggedUsers = sessionRegistry.getAllPrincipals();


            log.info("Sessions found:" + loggedUsers.size());
            for (Object principal : loggedUsers) {
                if(principal instanceof org.springframework.security.core.userdetails.User) {
                    final org.springframework.security.core.userdetails.User loggedUser = (org.springframework.security.core.userdetails.User) principal;
                    log.info("Found user session :" + loggedUser.getUsername());
                    if(user.getUsername().equals(loggedUser.getUsername())) {
                        List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                        if(null != sessionsInfo && sessionsInfo.size() > 0) {
                            for (SessionInformation sessionInformation : sessionsInfo) {
                                log.info("Expire now :" + sessionInformation.getSessionId());
//                                loggedUser.getAuthorities().clear();
//                                loggedUser.getAuthorities().add(new SimpleGrantedAuthority("ROLE_BLOCKED"));

                                sessionInformation.expireNow();
                                //sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                            }
                        }
                    }
                }
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/adminpanel";
    }


}
