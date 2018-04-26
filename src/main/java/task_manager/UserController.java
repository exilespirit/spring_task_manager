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

    @GetMapping("/user")
    public String user(Model model) {
        List<Task> list = taskRepository.findAll();

        model.addAttribute("tasks", list);
        return "user";
    }
}
