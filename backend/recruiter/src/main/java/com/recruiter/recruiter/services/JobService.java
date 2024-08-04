package com.recruiter.recruiter.services;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.dto.request.RequirementRequest;
import com.recruiter.recruiter.mapper.JobMapper;
import com.recruiter.recruiter.mapper.RequirementMapper;
import com.recruiter.recruiter.models.Job;
import com.recruiter.recruiter.models.Requirement;
import com.recruiter.recruiter.respository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
@NoArgsConstructor

public class JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper = JobMapper.INSTANCE;

    private RequirementMapper requirementMapper = RequirementMapper.INSTANCE;
    public Job createJob(JobRequest jobRequest) {
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
        return jobRepository.save(newJob);
    }


}
