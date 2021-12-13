package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.entity.RoleName;
import by.alex.busbooking.entity.User;
import by.alex.busbooking.mapper.UserMapper;
import by.alex.busbooking.repository.UserRepository;
import by.alex.busbooking.security.jwt.JwtUtil;
import by.alex.busbooking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @Override
    public Map<String, Object> login(String email, String password) {
        Map<String, Object> auth = new HashMap<>();
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = userRepository.findByEmail(email);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        userDTO.setPassword(null);
        if(user.getRoles().stream().noneMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
            userDTO.setRole("ROLE_USER");
        } else {
            userDTO.setRole("ROLE_ADMIN");
        }

        auth.put("token", jwtUtil.createToken(email, userRepository.findByEmail(email).getRoles()));
        auth.put("userDTO", userDTO);
        return auth;
    }
}
