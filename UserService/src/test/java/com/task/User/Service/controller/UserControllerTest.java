package com.task.User.Service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.Role;
import com.task.User.Service.model.entity.User;
import com.task.User.Service.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserServiceImpl userService;

    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setId(31);
        user.setName("makram");
        user.setAge(33);
        user.setEmail("makram31&yahoo.com");
        user.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        user.setRole(Role.valueOf("STUDENT"));

        UserDto userdto = new UserDto();
        userdto.setId(31);
        userdto.setName("makram");
        userdto.setAge(33);
        userdto.setEmail("makram31&yahoo.com");
        userdto.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        userdto.setRole(Role.valueOf("STUDENT"));

        userService.createUser(userdto);
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userdto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllUsers() throws Exception {
        UserDto user1 = new UserDto();
        user1.setName("akram");
        user1.setAge(54);
        user1.setEmail("akram4&yahoo.com");
        user1.setPassword("$2a$12$aDonh2KuRMVAWssE7YmCnOx4xqnGIhMuq/.U8.hgOXtaX.WwMuIRe");
        user1.setRole(Role.STUDENT);
        UserDto user2 = new UserDto();
        user2.setName("usama");
        user2.setAge(37);
        user2.setEmail("usama5@gmail.com");
        user2.setPassword("$2a$12$aDonh2KuRMVAWssE7YmCnOx4xqnGIhMuq/.U8.hgOXtaX.WwMuIRe");
        user2.setRole(Role.STUDENT);
        List<UserDto> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        userService.getAllUsers(1, 2);
        mockMvc.perform(get("/user/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(users))
                .param("pageNo", "1")
                .param("pageSize", "2")
        );
   }
    @Test
    void getUserById() throws Exception{
        UserDto userdto = new UserDto();
        userdto.setName("makram");
        userdto.setAge(33);
        userService.getUser(userdto.getId());
        mockMvc.perform(get("/user/get/{id}", userdto.getId())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(userdto))
        );
    }
    @Test
    void updateUser() throws Exception{
        User user = new User();
        user.setId(29);
        user.setName("rahma");
        user.setAge(24);
        user.setEmail("rahma29&yahoo.com");
        user.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");

        UserDto userDto = new UserDto();
        userDto.setId(29);
        userDto.setName("rahma");
        userDto.setAge(24);
        userDto.setEmail("rahma29&yahoo.com");
        userDto.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        userService.updateUser(userDto);
        mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto))
        );
    }

    @Test
    void deleteUser() throws Exception{
        User user = new User();
        user.setId(30);

        userService.deleteUser(user.getId());

        mockMvc.perform(delete("/user/delete/{id}",user.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
    }
}