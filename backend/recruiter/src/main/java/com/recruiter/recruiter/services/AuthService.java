package com.recruiter.recruiter.services;

import com.recruiter.recruiter.configuration.CustomUserDetailService;
import com.recruiter.recruiter.configuration.CustomUserDetails;
import com.recruiter.recruiter.dto.request.RequestLogin;
import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.dto.response.ResponseLogin;
import com.recruiter.recruiter.dto.response.UserResponse;
import com.recruiter.recruiter.exception.DataExists;
import com.recruiter.recruiter.exception.NotFoundEx;
import com.recruiter.recruiter.jwt.JwtProvider;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;
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
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtProvider jwtProvider;
    @Autowired
    private final AuthenticationManager authenticationManager;
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
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); //encode to protect in database
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
        authRepository.save(user);

        log.info("user: {}", user);
        verifyRegisterRepository.deleteByToken(token);
        return "Congratulation. Active your account successfully!";
    }


    public ResponseLogin login(RequestLogin userLogin) {

//        Optional<User> userData = authRepository.findByUsername(userLogin.getUsername());

        // Create UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userLogin.getUsername(), userLogin.getPassword());

        // Authenticate user - spring auto check username and password with authenticationManager -need to define in config security
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        CustomUserDetails customUserDetails =  (CustomUserDetails)authentication.getPrincipal();
        boolean isEnableLogin =     customUserDetails.isEnabled();
        System.out.println("isEnableLogin " + isEnableLogin);
        System.out.println("user   " + ( customUserDetails).getUsername() + " ))++  " + customUserDetails.getIdUserLogin());
//        if(isEnableLogin) {
//            throw new AuthenticationException("Please active your account before login") {
//            };
//        }
        // Set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtProvider.generateToken(authentication);

        // Return response with token
        return new ResponseLogin( token, userLogin.getUsername());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Nếu UserDetails chứa thông tin về User của bạn, bạn có thể trả về User.
            // Nếu UserDetails không phải là một đối tượng User, bạn có thể lấy thông tin người dùng từ userDetails.

            return (User) userDetails; // Điều này giả định rằng UserDetails là User, có thể cần chuyển đổi hoặc truy vấn thêm nếu không.
        }

        return null; // Hoặc xử lý lỗi nếu không tìm thấy người dùng
    }
}
