package com.recruiter.recruiter.services;

import com.recruiter.recruiter.configuration.CustomUserDetailService;
import com.recruiter.recruiter.dto.request.RequestLogin;
import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.dto.response.ResponseLogin;
import com.recruiter.recruiter.dto.response.UserResponse;
import com.recruiter.recruiter.exception.DataExists;
import com.recruiter.recruiter.exception.NotFoundEx;
import com.recruiter.recruiter.mapper.UserMapper;
import com.recruiter.recruiter.models.User;
import com.recruiter.recruiter.models.VerifyToken;
import com.recruiter.recruiter.respository.AuthRepository;
import com.recruiter.recruiter.respository.VerifyRegisterRepository;
import com.recruiter.recruiter.utils.EnumRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor //auto create constructor with final declare
@Slf4j
public class AuthService {
    @Autowired
    private final AuthRepository authRepository;
    @Autowired
    private  final UserMapper userMapper = UserMapper.INSTANCE.INSTANCE;

    @Autowired
    private final  EmailService emailService;
    @Autowired
    private final MailContentBuilderService mailContentBuilderService;

    //test thử not use jwt
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private final VerifyRegisterRepository verifyRegisterRepository;
    @Transactional
    public String  signUp(UserRequest userRequest) {
        Boolean checkUserExist = authRepository.existsByUsername(userRequest.getUsername());
        if(checkUserExist) {
            throw new DataExists();
        }
        userRequest.setRole(EnumRole.lookupRole(userRequest));
        User user = userMapper.userRequestToUser(userRequest);
        //default is false, active link in mail enable true
        user.setEnableLogin(false);
        userMapper.userToUserResponse(authRepository.save(user));
        String token = generateVerifyRegister(user);
        String mailContent = mailContentBuilderService.buiderContentMail(userRequest.getEmail(), token);
        emailService.sendEmail(userRequest.getEmail(), "Active your account to finish register", mailContent);
        return token  ;
    }


    public String generateVerifyRegister(User user) {
        String token = UUID.randomUUID().toString();
        VerifyToken verifyToken =  VerifyToken.builder()
                .dateCreated(Instant.now())
                .user(user)
                .token(token).build();
        verifyRegisterRepository.save(verifyToken);
        return token;
    }

    @Transactional
    public String verifyRegisterToken(String token) {
        VerifyToken verifyToken = verifyRegisterRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundEx("token register is not valid or expired"));

        User user = verifyToken.getUser();
        user.setEnableLogin(true);

        log.info("user: {}", user);
        verifyRegisterRepository.deleteByToken(token);
        return "Congratulation. Active your account successfully!";
    }


    public ResponseLogin login(RequestLogin userLogin) {
        //try implementing here not using jwt filter
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userLogin.getUsername());

        // set userDetail to UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return new ResponseLogin("", userLogin.getUsername());
    }
}
