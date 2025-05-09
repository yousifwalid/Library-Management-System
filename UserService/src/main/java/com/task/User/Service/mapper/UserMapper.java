package com.task.User.Service.mapper;


import com.task.User.Service.model.dto.UserDto;
import com.task.User.Service.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
