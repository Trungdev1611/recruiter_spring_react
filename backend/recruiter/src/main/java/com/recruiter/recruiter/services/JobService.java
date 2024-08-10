package com.recruiter.recruiter.services;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.dto.request.RequirementRequest;
import com.recruiter.recruiter.dto.response.JobResponse;
import com.recruiter.recruiter.mapper.JobMapper;
import com.recruiter.recruiter.mapper.RequirementMapper;
import com.recruiter.recruiter.models.Job;
import com.recruiter.recruiter.models.Requirement;
import com.recruiter.recruiter.respository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
@AllArgsConstructor

public class JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper = JobMapper.INSTANCE;

    private RequirementMapper requirementMapper = RequirementMapper.INSTANCE;
    private AuthService authService;
    public JobResponse createJob(JobRequest jobRequest) {
        Job newJob = jobMapper.jobRequestToJob(jobRequest);
        //requirementRequest to requirement
        List<RequirementRequest> requirementRequestList = jobRequest.getRequirementsRequests();
        List<Requirement> requirementList = requirementRequestList.stream()
                .map(requirementRequest -> {
                    Requirement requirement = requirementMapper.requirementRequestToRequirement(requirementRequest);
                    requirement.setJob(newJob);
                    return requirement;
                })
                .toList();

        newJob.setRequirements(requirementList);
        newJob.setEmployer(authService.getCurrentUser());

        newJob.setCreated_at(Instant.now()); //set time create or use @PrePersist in controller also work perfectly
        Job jobData = jobRepository.save(newJob);

        JobResponse jobResponse = jobMapper.jobToJobResponse(jobData);
        jobResponse.setRequirementResponses(
                jobData.getRequirements().stream().
                        map(item -> requirementMapper.requirementToRequirementResponse(item)).toList());
        return jobResponse;
    }


}
