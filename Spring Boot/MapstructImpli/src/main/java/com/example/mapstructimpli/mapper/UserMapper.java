package com.example.mapstructimpli.mapper;

import com.example.mapstructimpli.dto.UserDto;
import com.example.mapstructimpli.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "user.username", target = "name", defaultValue = "Siva")
    UserDto modelToDto(User user);
//    @Mapping(source = "userDto.name", target = "username", defaultValue = "Siva")
    @InheritInverseConfiguration
    User dtoToModel(UserDto userDto);
    List<UserDto> modelsToDtos(List<User> users);

    List<User> dtosToModels(List<UserDto> userDtos);



}
