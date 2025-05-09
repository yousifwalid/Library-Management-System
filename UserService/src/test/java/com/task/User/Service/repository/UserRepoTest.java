package com.task.User.Service.repository;

import com.task.User.Service.model.entity.Role;
import com.task.User.Service.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepoTest {

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
        userRepo.save(user);

        Assertions.assertNotNull(user);
    }
    @Test
    void getAllUsers() {
        User user = new User();
        user.setId(5);
        user.setName("jack");
        User user1 = new User();
        user1.setId(6);
        user1.setName("baha");
        userRepo.save(user);
        userRepo.save(user1);
        List<User> users = userRepo.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }
    @Test
    void getUserById() {
        User user = new User();
        user.setName("karam");
        user.setAge(33);
        user.setEmail("karam4&yahoo.com");
        user.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        user.setRole(Role.valueOf("STUDENT"));
        userRepo.save(user);
        User list = userRepo.findById(user.getId()).get();
        Assertions.assertNotNull(list);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setName("karam");
        user.setAge(33);
        user.setEmail("karam4&yahoo.com");
        user.setPassword("$2a$12$FV11U.rSwKHHfGO6XrDx6OZtHxqJr6zV/QNtE4rnmM2BG1cDRWEie");
        user.setRole(Role.valueOf("STUDENT"));
        userRepo.save(user);
        User list = userRepo.findById(user.getId()).get();
        list.setName("hamada");
        list.setAge(36);
        userRepo.save(list);
        Assertions.assertNotNull(list);
    }

    @Test
    void deleteUser() {
        User user = User.builder()
                .name("karam")
                .build();
        userRepo.save(user);
        userRepo.deleteById(user.getId());
        Optional<User> user1 = userRepo.findById(user.getId());
        Assertions.assertFalse(user1.isPresent());
    }
}
