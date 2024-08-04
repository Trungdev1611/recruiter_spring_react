package com.recruiter.recruiter.respository;

import com.recruiter.recruiter.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
