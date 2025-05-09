package com.task.User.Service.service;

import com.task.User.Service.exception.UserAlreadyExistsException;
import com.task.User.Service.exception.UserNotFoundException;
import com.task.User.Service.mapper.UserMapper;
import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.User;
import com.task.User.Service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto) {
        Optional<User> user = userRepo.findById(userDto.getId());
        if (user.isEmpty()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userRepo.save(userMapper.toEntity(userDto));
        } else {
            log.info("User is already existing");
            throw new UserAlreadyExistsException("User is already existing");
        }
    }

    @Query(value = "SELECT name FROM users WHERE pageNo=?1 AND pageSize=?2")    //return 2 records
    @Override
    public List<UserDto> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> pages = userRepo.findAll(pageable);
        List<User> employeeList = pages.getContent();
        return employeeList.stream().map(s -> userMapper.toDto(s)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "users",key = "#id")
    public UserDto getUser(int id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        } else {
            log.error("User is not found");
            throw new UserNotFoundException("User with this id is not found");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepo.findById(userDto.getId()).get();
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        existingUser.setName(userDto.getName());
//        existingUser.setAge(userDto.getAge());
//        existingUser.setId(userDto.getId());
//        existingUser.setEmail(userDto.getEmail());
        return userMapper.toDto(userRepo.save(existingUser));
    }
    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
