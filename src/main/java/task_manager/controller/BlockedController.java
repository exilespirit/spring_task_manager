package task_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import task_manager.model.User;

@Controller
public class BlockedController {

    @GetMapping("/blocked")
    public String showBlockedPage() {

        return "blocked";
    }
}
