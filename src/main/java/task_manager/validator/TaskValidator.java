package task_manager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import task_manager.model.Task;
import task_manager.model.TaskRepository;

@Component
public class TaskValidator implements Validator {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty");
        if (task.getTitle().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        if (task.getTitle().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");

    }
}
