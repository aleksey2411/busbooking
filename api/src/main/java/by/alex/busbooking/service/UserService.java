package by.alex.busbooking.service;

import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.entity.RoleName;

import java.util.List;

public interface UserService {
    UserDTO getUser(int id);
    UserDTO updateUser(UserDTO userDto);
    void deleteUser(int id);
    List<UserDTO> getUsers();
    UserDTO addUser(UserDTO userDto);
    List<Object> getMostActiveUserLastMonth(int busId);
    UserDTO findByEmail(String email);
}
