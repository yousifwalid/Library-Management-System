package com.task.User.Service.service;

import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.Role;
import com.task.User.Service.model.entity.User;
import com.task.User.Service.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepo userRepo;

    @Test
    void createUser(){
        User user = new User();
        user.setId(4);
        user.setName("karam");
        user.setAge(33);
        user.setEmail("karam4&yahoo.com");
        user.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        user.setRole(Role.valueOf("STUDENT"));
        UserDto userdto = new UserDto();
        userdto.setId(4);
        userdto.setName("karam");
        userdto.setAge(33);
        userdto.setEmail("karam4&yahoo.com");
        userdto.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        userdto.setRole(Role.valueOf("STUDENT"));
        userRepo.save(user);
        User savedUser = userService.createUser(userdto);
        Assertions.assertNotNull(savedUser);
    }
    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setName("akram");
        user1.setAge(54);
        user1.setEmail("akram4&yahoo.com");
        user1.setPassword("$2a$12$aDonh2KuRMVAWssE7YmCnOx4xqnGIhMuq/.U8.hgOXtaX.WwMuIRe");
        user1.setRole(Role.STUDENT);
        User user2 = new User();
        user2.setName("usama");
        user2.setAge(37);
        user2.setEmail("usama5@gmail.com");
        user2.setPassword("$2a$12$aDonh2KuRMVAWssE7YmCnOx4xqnGIhMuq/.U8.hgOXtaX.WwMuIRe");
        user2.setRole(Role.STUDENT);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        userRepo.findAll(PageRequest.of(0,2));
        List<UserDto> userDtoList = userService.getAllUsers(0, 2);
        Assertions.assertAll(
                  ()-> assertEquals(2,userDtoList.size()));
    }
    @Test
    void getUserById() {
        User user = new User();
        user.setName("karam");
        user.setAge(33);
        user.setEmail("karam4&yahoo.com");
        userRepo.findById(user.getId());
        UserDto userDto = userService.getUser(user.getId());
        assertNotNull(userDto);
    }

    @Test
    void updateUser() {
        User user = User.builder().id(28).name("karam").password("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie").build();
        UserDto userDto = UserDto.builder().id(28).name("karam").password("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie").build();
        userRepo.findById(user.getId());
        user.setName("tamer");
        userDto.setName("tamer");
        userRepo.save(user);
        UserDto updatedUser = userService.updateUser(userDto);
        Assertions.assertNotNull(updatedUser);
    }

    @Test
    void deleteUser() {
        User user = User.builder()
                .id(13)
                .name("baha")
                .build();
        userRepo.findById(user.getId());
        Assertions.assertAll(()-> userService.deleteUser(user.getId()));
    }
}