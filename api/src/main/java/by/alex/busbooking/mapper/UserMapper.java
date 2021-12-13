package by.alex.busbooking.mapper;

import by.alex.busbooking.entity.User;
import by.alex.busbooking.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class UserMapper {
     public abstract UserDTO userToUserDTO(User user);

     public abstract User userDTOtoUser(UserDTO userDTO);

     public abstract List<UserDTO> userToUserDTO(List<User> users);
}
