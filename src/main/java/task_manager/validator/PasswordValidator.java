package task_manager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import task_manager.model.User;
import task_manager.model.UserRepository;

@Component
public class PasswordValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        User targetUser = userRepository.findById(user.getId()).get();
        if (!passwordEncoder.matches(user.getPassword(),targetUser.getPassword())) {
            errors.rejectValue("password", "Wrong.password");
        }
    }
}