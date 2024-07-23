package com.recruiter.recruiter.utils;

import com.recruiter.recruiter.dto.request.UserRequest;

import java.util.Arrays;

public enum EnumRole {
    ADMIN, EMPLOYER, EMPLOYEE;
    public static EnumRole lookupRole(UserRequest userRequest) {
        return Arrays.stream(EnumRole.values()).
                filter(valueInEnum -> valueInEnum == userRequest.getRole()) //method name to get value enum
                .findFirst()
                .orElse(EnumRole.EMPLOYEE); //default is Employee
    }

}
