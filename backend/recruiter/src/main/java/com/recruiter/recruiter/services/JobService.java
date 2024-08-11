package com.recruiter.recruiter.services;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.dto.request.RequirementRequest;
import com.recruiter.recruiter.dto.response.JobResponse;
import com.recruiter.recruiter.dto.response.RequirementResponse;
import com.recruiter.recruiter.exception.NotFoundEx;
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
import java.util.Optional;

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

    public List<JobResponse> getListJob() {
        List<Job> jobList = jobRepository.findAll();
        return jobList.stream().map(jobItem -> jobMapper.jobToJobResponse(jobItem)).toList();
    }

    public JobResponse getJobDetail(Long idJob) {
        Job jobDetail = jobRepository.findById(idJob).orElseThrow(() -> new NotFoundEx("job not found "));
        List<RequirementResponse> requirementResponses =
                jobDetail.getRequirements().stream().map(requireMent ->
                requirementMapper.requirementToRequirementResponse(requireMent)).toList();
//        System.out.println("requiment"+ requirementResponses);
        JobResponse jobResponse =  (jobMapper.jobToJobResponse(jobDetail));
        jobResponse.setRequirementResponses(requirementResponses);
        return jobResponse;
    }
}
