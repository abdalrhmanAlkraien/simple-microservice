package junit.junit.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.junit.Service.DTO.UserDto;
import junit.junit.Service.Implements.UserService;
import junit.junit.repository.implement.InMemory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    public void getAllUserCheckRequest() throws Exception {
        UserDto userDto = new UserDto(1L, "mohamd", "ali", 25);
        UserDto userDto1 = new UserDto(2L, "kalid", "ali", 25);

        List<UserDto> users = Arrays.asList(userDto, userDto1);

        when(userService.getUsers()).thenReturn(users);

        mockMvc
                .perform(get("/user"))
                .andExpect(status().isOk());

        System.out.println("is check request test for getAllUser pass ? ".concat("Pass"));

    }

    @Test
    public void getAllUserWithJson() throws Exception {
        UserDto userDto = new UserDto(1L, "mohamd", "ali", 25);
        UserDto userDto1 = new UserDto(2L, "kalid", "ali", 25);

        List<UserDto> users = Arrays.asList(userDto, userDto1);

        //when(userService.getUsers()).thenReturn(users);
        given(userService.getUsers()).willReturn(users);
        mockMvc
                .perform(get("/user").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", equalTo("mohamd")))
                .andDo(print())
                .andExpect(jsonPath("$[1].id", equalTo(2)));

        System.out.println("is check request test for getAllUser with json  pass ? ".concat("Pass"));

    }

    @Test
    public void getUserById() throws Exception {
        UserDto user = new UserDto(2L, "abed", "alkraien", 26);

        when(userService.getUserById(2L)).thenReturn(user);

        mockMvc
                .perform(get("/user")
                        .contentType("application/json;charset=UTF-8")
                        .param("id", String.valueOf(2L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.firstName", equalTo("abed")))
                .andExpect(jsonPath("$.age", greaterThanOrEqualTo(25)));

        System.out.println("is check request test for geUserById with json  pass ? ".concat("Pass"));

    }

    @Test
    public void createUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDto user = new UserDto(2L, "abed", "alkraien", 26);
        mapper.writeValueAsString(user);

        when(userService.registry(user)).thenReturn(user);

        mockMvc
                .perform(post("/user")
                        .contentType("application/json;charset=UTF-8")
                        .content(mapper.writeValueAsBytes(user)))
                .andExpect(status().isCreated());


    }

    @Test
    public void deleteUser() {
        UserDto user = new UserDto(2L, "abed", "alkraien", 26);

    }

}
