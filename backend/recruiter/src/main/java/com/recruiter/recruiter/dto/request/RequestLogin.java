package com.recruiter.recruiter.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {
    @NotBlank(message = "Username is not valid")
    private String username;
    @NotBlank(message = "password is not valid")
    private String password;

}
