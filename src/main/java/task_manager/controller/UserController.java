package task_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task_manager.model.Task;
import task_manager.model.TaskRepository;
import task_manager.model.User;
import task_manager.model.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);




    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String user(@RequestParam(name="id", required=true) String id, Model model) {
        model.addAttribute("id", id);
        User user = userRepository.findById(Long.parseLong(id)).get();
        model.addAttribute("user", user);
        List<Task> list = taskRepository.findAllByUserId(Long.parseLong(id));
        model.addAttribute("tasks", list);

        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        return "user";
    }

}
