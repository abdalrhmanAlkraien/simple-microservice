package junit.junit.Service;

import junit.junit.Service.DTO.UserDto;
import junit.junit.model.User;

import java.util.List;

public interface IUserService {
    public UserDto registry(UserDto userDto);

    public List<UserDto> getUsers();

    public UserDto getUserById(Long id);

    public void deleteUser(Long id);

    public void deleteUser(UserDto userDto);

    public void updateUser(UserDto userDto);
}
