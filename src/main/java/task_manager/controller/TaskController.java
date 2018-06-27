package task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task_manager.validator.TaskValidator;
import task_manager.model.Task;
import task_manager.model.TaskRepository;
import task_manager.model.UserRepository;


@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TaskValidator taskValidator;

    @GetMapping("/task")
    public String showTaskPage(@RequestParam(name="id", required=true) String id, @RequestParam(name="uid", required=true) String uid, Model model) {
        model.addAttribute("id", id);
        Task task = taskRepository.findById(Long.parseLong(id)).get();
        model.addAttribute("taskForm", task);
        model.addAttribute("uid", uid);
        model.addAttribute("delButton", "visible");
        return "task";
    }

    @GetMapping("/task/new")
    public String showNewTaskPage(@RequestParam(name="uid", required=true) String uid, Model model) {
        Task task = new Task();
        model.addAttribute("taskForm", task);
        model.addAttribute("delButton", "hidden");
        model.addAttribute("uid", uid);
        return "task";
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST, params="save")
    public String saveTask(@ModelAttribute("uid") Long uid, @ModelAttribute("taskForm") Task task, BindingResult result, Model model){
        task.setUser(userRepository.findById(uid).get());
        taskValidator.validate(task, result);
        if (result.hasErrors()) {
            return "task";
        }
        taskRepository.save(task);
        return "redirect:/user?id="+uid;
    }

    @RequestMapping(value = "/task/new", method = RequestMethod.POST, params="save")
    public String saveNewTask(@ModelAttribute("uid") Long uid, @ModelAttribute("taskForm") Task task, BindingResult result, Model model){
        task.setUser(userRepository.findById(uid).get());
        taskValidator.validate(task, result);
        if (result.hasErrors()) {
            model.addAttribute("delButton", "hidden");
            return "task";
        }
        taskRepository.save(task);
        return "redirect:/user?id="+uid;
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST,  params="delete")
    public String deleteTask(@ModelAttribute("uid") Long uid, @ModelAttribute("taskForm") Task task, BindingResult result, Model model){
        //Long uid = (taskRepository.findById(task.getId()).get()).getUser().getId();
        taskRepository.delete(task);
        return "redirect:/user?id="+uid;
    }

    @RequestMapping(value = {"/task", "/task/new"}, method = RequestMethod.POST, params="cancel")
    public String cancelTask(@ModelAttribute("uid") Long uid, Model model){
        //Task task = taskRepository.findById(Long.parseLong(id)).get();
        return "redirect:/user?id="+uid;
    }

}
