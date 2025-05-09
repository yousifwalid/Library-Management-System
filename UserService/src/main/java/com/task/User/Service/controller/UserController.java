package com.task.User.Service.controller;

import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.User;
import com.task.User.Service.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping("/getAll")          //localhost:9093/user/getAll?pageNo=0&pageSize=5
    public List<UserDto> getAllUsers(@RequestParam int pageNo , @RequestParam int pageSize){
        return userService.getAllUsers(pageNo, pageSize);
    }
    @GetMapping("/get/{id}")
    public UserDto getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @PutMapping("/update")
    public UserDto updateEmployee(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
