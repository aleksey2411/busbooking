package by.alex.busbooking.validation.user;

import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        UserDTO userByEmail = userService.findByEmail(userDTO.getEmail());
        if (userByEmail != null && userDTO.getId() != userByEmail.getId()) {
            errors.rejectValue("email", "error.userDTO.userExists.email");
        }
    }
}
