package junit.junit.Service.Implements;

import junit.junit.Service.DTO.UserDto;
import junit.junit.Service.IUserService;
import junit.junit.model.User;
import junit.junit.repository.Repository;
import junit.junit.repository.implement.InMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    //    @Autowired
    private final InMemory inMemory;

    public UserService(InMemory inMemory) {
        this.inMemory = inMemory;
    }

    @Override
    public UserDto registry(UserDto userDto) {
        User user = userDto.ToDTO(userDto);
        userDto = user.toEntity(inMemory.save(user));
        return userDto;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> user = inMemory.getAll();
        User user1 = new User();
        return user1.toEntity(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = inMemory.getById(id).get();
        return user.toEntity(user);
    }

    @Override
    public void deleteUser(Long id) {
        inMemory.deleteUser(id);
    }

    @Override
    public void deleteUser(UserDto userDto) {

    }

    @Override
    public void updateUser(UserDto userDto) {

    }
}
