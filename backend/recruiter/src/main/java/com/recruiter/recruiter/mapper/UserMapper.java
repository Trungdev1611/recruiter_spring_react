package com.recruiter.recruiter.mapper;

import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.dto.response.UserResponse;
import com.recruiter.recruiter.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //UserMapper INSTANCE: Khởi tạo một instance của mapper sử dụng Mappers.getMapper(UserMapper.class).
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    User userRequestToUser(UserRequest userRequest);
    UserResponse userToUserResponse(User user);
}
