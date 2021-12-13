package by.alex.busbooking.service.impl;

import by.alex.busbooking.dto.BusDTO;
import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.entity.Bus;
import by.alex.busbooking.entity.Role;
import by.alex.busbooking.entity.RoleName;
import by.alex.busbooking.entity.User;
import by.alex.busbooking.mapper.BusMapper;
import by.alex.busbooking.mapper.UserMapper;
import by.alex.busbooking.repository.BusRepository;
import by.alex.busbooking.repository.RoleRepository;
import by.alex.busbooking.repository.UserRepository;
import by.alex.busbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BusRepository busRepository;
    private final UserMapper userMapper;
    private final BusMapper busMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO getUser(int id) {
        User user = userRepository.getById(id);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        userRepository.getById(userDTO.getId());
        User updatedUser = userMapper.userDTOtoUser(userDTO);
        userRepository.save(updatedUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.userToUserDTO(users);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.userDTOtoUser(userDTO);

        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setActive(true);
        Role role = roleRepository.findByRole(RoleName.ROLE_USER.name());
        user.setRoles(new HashSet<>(List.of(role)));

        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public List<Object> getMostActiveUserLastMonth(int busId) {
        final LocalDate initial = LocalDate.now().minusMonths(1);
        final LocalDateTime startDate = initial.withDayOfMonth(1).atStartOfDay();
        final LocalDateTime endDate = initial.withDayOfMonth(initial.lengthOfMonth()).atTime(LocalTime.MAX);

        User user = userRepository.getMostActiveUserLastMonthByBusId(busId, startDate, endDate);
        Bus bus = busRepository.getById(busId);

        UserDTO userDTO = userMapper.userToUserDTO(user);
        BusDTO busDTO = busMapper.busToBusDTO(bus);

        return List.of(userDTO, busDTO);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.userToUserDTO(user);
    }
}
