package com.project.EMS.security.mapper;

import com.project.EMS.entity.User;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(CreateUserRequest createUserRequest);
    UserResponse toUserResponse(User user);
}
