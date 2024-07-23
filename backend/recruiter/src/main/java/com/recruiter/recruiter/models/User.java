package com.recruiter.recruiter.models;

import com.recruiter.recruiter.utils.EnumRole;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor

@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idUser;
    private  String username;
    private  String password;
    private  String email;
    @Enumerated(EnumType.STRING)
    private  EnumRole role; //value of role must be Enum

    private Boolean enableLogin; //this field is true (validate mail) to login
}
