package com.recruiter.recruiter.dto.request;

import com.recruiter.recruiter.utils.EnumRole;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username is not valid")
    private String username;
    @NotBlank(message = "password is not valid")
    private String password;
    @NotBlank(message = "email is not valid")
    private String email;

    private EnumRole role;


}
