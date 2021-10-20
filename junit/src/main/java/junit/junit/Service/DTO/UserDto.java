package junit.junit.Service.DTO;

import junit.junit.model.User;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    public UserDto() {
    }

    public User ToDTO(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        return user;

    }
}
