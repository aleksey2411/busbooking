package by.alex.busbooking.controller;

import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.dto.auth.AuthRequestDTO;
import by.alex.busbooking.dto.auth.AuthResponseDTO;
import by.alex.busbooking.service.AuthService;
import by.alex.busbooking.service.UserService;
import by.alex.busbooking.validation.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final UserValidator userValidator;

    @InitBinder("userDTO")
    protected void userValidator(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @PostMapping("/registration")
    public UserDTO registration(@RequestBody @Valid UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO loginForUser(@RequestBody AuthRequestDTO authRequestDTO) {
        Map<String, Object> auth = authService.login(authRequestDTO.getEmail(), authRequestDTO.getPassword());

        String token = (String) auth.get("token");
        UserDTO userDTO = (UserDTO) auth.get("userDTO");

        return new AuthResponseDTO(token, userDTO);
    }
}
