package task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import task_manager.model.Task;
import task_manager.model.TaskRepository;

import java.util.Optional;


@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/task")
    public String task(@RequestParam(name="id", required=false, defaultValue="new") String id, Model model) {
        model.addAttribute("id", id);
        if (id != "new") {
            Task task = taskRepository.findById(Long.parseLong(id)).get();
            model.addAttribute("task", task);
        }
        return "task";
    }

    public String saveTask(@ModelAttribute("task") Task task, Model model){
        return "task";
    }
}
