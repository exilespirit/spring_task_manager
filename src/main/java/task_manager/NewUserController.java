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

    @GetMapping("/user/update")
    public String updateUser(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        User user = userRepository.findById(Long.parseLong(id)).get();
        model.addAttribute("newUserForm", user);

        return "newuser";
    }


    @RequestMapping(value = {"/user/update", "/newuser"}, method = RequestMethod.POST, params="save")
    public String saveUser(@ModelAttribute("newUserForm") User user,
                              BindingResult result, Model model) {
        userRepository.save(user);
        return "redirect:/user?id="+user.getId();
    }

    @RequestMapping(value = {"/user/update", "/newuser"}, method = RequestMethod.POST, params="cancel")
    public String cancelUser(@ModelAttribute("newUserForm") User user,
                           BindingResult result, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = {"/user/update", "/newuser"}, method = RequestMethod.POST, params="delete")
    public String deleteUser(@ModelAttribute("newUserForm") User user,
                             BindingResult result, Model model) {
        userRepository.delete(user);
        return "redirect:/";
    }

}
