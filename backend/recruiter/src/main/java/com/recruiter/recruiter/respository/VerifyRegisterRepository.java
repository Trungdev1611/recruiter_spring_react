package com.recruiter.recruiter.respository;

import com.recruiter.recruiter.models.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface VerifyRegisterRepository extends JpaRepository< VerifyToken, Long> {
    Optional<VerifyToken> findByToken(String token);
    void deleteByToken(String token);
}
