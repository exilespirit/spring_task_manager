package task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import task_manager.model.Task;
import task_manager.model.TaskRepository;
import task_manager.model.User;
import task_manager.model.UserRepository;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String user(@RequestParam(name="id", required=false, defaultValue="new") String id, Model model) {
        model.addAttribute("id", id);
        if (id != "new") {
            User user = userRepository.findById(Long.parseLong(id)).get();
            model.addAttribute("user", user);
        }
        //List<Task> list = taskRepository.findAll();
        List<Task> list = taskRepository.findAllByUserId(Long.parseLong(id));

        model.addAttribute("tasks", list);
        return "user";
    }
}
