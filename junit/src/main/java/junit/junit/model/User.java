package junit.junit.model;

import junit.junit.Service.DTO.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;


    public UserDto toEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public List<UserDto> toEntity(List<User> user) {
        return user
                .stream()
                .map(x -> x.toEntity(x))
                .collect(Collectors.toList());
    }


}
