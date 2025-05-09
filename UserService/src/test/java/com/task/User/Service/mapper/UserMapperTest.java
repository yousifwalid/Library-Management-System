package com.task.User.Service.mapper;

import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.Role;
import com.task.User.Service.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void MapUserToDto() {
        User user = new User();
        user.setId(1);
        user.setName("hasan");
        user.setPassword("$2a$10$dyICex9wpP/KX2fCJowUS.06Dg2BYxpR3vz0hRn4W96fhu6zc78M6");
        user.setEmail("hasan@gmail.com");
        user.setAge(24);
        user.setRole(Role.ADMIN);

        UserDto userDto = userMapper.toDto(user);

        Assertions.assertAll(
                () -> assertEquals(userDto.getId(), user.getId()),
                () -> assertEquals(userDto.getName(), user.getName()),
                () -> assertEquals(userDto.getPassword(), user.getPassword()),
                () -> assertEquals(userDto.getEmail(), user.getEmail()),
                () -> assertEquals(userDto.getAge(), user.getAge()),
                () -> assertEquals(userDto.getRole(), user.getRole())
        );
    }

    @Test
    void MapDtoToEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("hasan");
        userDto.setPassword("$2a$10$dyICex9wpP/KX2fCJowUS.06Dg2BYxpR3vz0hRn4W96fhu6zc78M6");
        userDto.setEmail("hasan@gmail.com");
        userDto.setAge(24);
        userDto.setRole(Role.ADMIN);

        User user = userMapper.toEntity(userDto);

        Assertions.assertAll(
                () -> assertEquals(user.getId(), userDto.getId()),
                () -> assertEquals(user.getName(), userDto.getName()),
                () -> assertEquals(user.getPassword(), userDto.getPassword()),
                () -> assertEquals(user.getEmail(), userDto.getEmail()),
                () -> assertEquals(user.getAge(), userDto.getAge()),
                () -> assertEquals(user.getRole(), userDto.getRole())
        );

    }
}