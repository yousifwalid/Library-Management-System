package com.task.User.Service.service;


import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    List<UserDto> getAllUsers(int pageNo , int pageSize);
    UserDto getUser(int id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(int id);
}
