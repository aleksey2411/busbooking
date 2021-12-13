package by.alex.busbooking.dto.auth;

import by.alex.busbooking.dto.UserDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponseDTO {
    private final String token;
    private final UserDTO user;
}
