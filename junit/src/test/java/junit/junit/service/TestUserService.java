package junit.junit.service;

import junit.junit.Service.DTO.UserDto;
import junit.junit.Service.Implements.UserService;
import junit.junit.model.User;
import junit.junit.repository.Repository;
import junit.junit.repository.implement.InMemory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    @Spy
    InMemory inMemoryGet;


    @InjectMocks
    UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registry() {

        UserDto emp = new UserDto(1L, "Lokesh", "Gupta", 25);

        when(inMemoryGet.save(emp.ToDTO(emp))).thenReturn(emp.ToDTO(emp));
        verify(inMemoryGet, times(0)).save(emp.ToDTO(emp));
        userService.registry(emp);
        verify(inMemoryGet, times(1)).save(emp.ToDTO(emp));
        System.out.println("\"is create new user test pass? \" = " + "Pass");
    }

    @Test
    public void getAllList() {
        List<User> users = new ArrayList<User>();
        users.add(new User(1L, "abed", "alrhman", 26));
        users.add(new User(2L, "abdallah", "almasre", 27));
        users.add(new User(3L, "bisher", "alahmad", 35));
        users.add(new User(4L, "ibrahem", "alrabe", 32));

        when(inMemoryGet.getAll()).thenReturn(users);

        List<UserDto> userDTOs = userService.getUsers(); // 1 times
        assertEquals(4, userDTOs.size());
        verify(inMemoryGet, times(1)).getAll();


        users.add(new User(5L, "ibrahem", "alrabe", 32));

        users.add(new User(6L, "ibrahem", "alrabe", 32));
        when(inMemoryGet.getAll()).thenReturn(users);
        userDTOs = userService.getUsers(); // 2 times
        int userSize = userService.getUsers().size();// 3 times
        assertEquals(6, userDTOs.size());
        verify(inMemoryGet, times(3)).getAll();
        System.out.println("\"is get all user test pass?\" = " + "Pass");

    }

    @Test
    public void getUserById() {
        Long id = 1L;
        User user = new User(id, "abed", "mohamed", 26);
        when(inMemoryGet.getById(any())).thenReturn(Optional.of(new User(id, "abed", "mohamed", 26)));

        UserDto user1 = userService.getUserById(1L);
        assertEquals(id, user1.getId());
        assertEquals("abed", user1.getFirstName());

        System.out.println("\"is get user by id test pass?\" = " + "Pass");
    }


    @Test(expected = NullPointerException.class)
    public void notFoundUser() throws Exception {
        Long id = 5L;
        User user = new User(2L, "abed", "mohamed", 26);

        when(inMemoryGet.getById(id)).thenReturn(Optional.of(user));
        userService.getUserById(id);
        verify(inMemoryGet, times(1)).getById(any()).get();
        System.out.println("\"Null Pointer Exception Test is Pass\" = " + "Pass");
    }

    @Test
    public void deleteUser() {
        User user = new User(2L, "abed", "mohamed", 26);
        userService.deleteUser(anyLong());
        verify(inMemoryGet, times(1)).deleteUser(2L);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteUserWhenUserNotExist() {
        User user = new User(2L, "abed", "mohamed", 26);
        userService.deleteUser(2L);
        verify(userService, times(1)).deleteUser(2L);
    }

}
