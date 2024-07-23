package com.recruiter.recruiter.configuration;

import com.recruiter.recruiter.exception.NotFoundEx;
import com.recruiter.recruiter.models.User;
import com.recruiter.recruiter.respository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username).orElseThrow(() -> new NotFoundEx("Username or password is not valid"));

        //one user has only one role, so that why I make it simple as bellow
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name())) ; //.name method to get String of enum

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                //  new HashSet<GrantedAuthority>()// cái này là role empty
                authorities
                ) ;
    }
}
