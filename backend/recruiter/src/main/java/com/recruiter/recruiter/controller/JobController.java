package com.recruiter.recruiter.controller;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.services.JobService;
import com.recruiter.recruiter.utils.Response;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @Secured("ROLE_EMPLOYER") // Ensure only users with the employer role can access this endpoint
    @PostMapping("create-job")
    ResponseEntity<Response> createJob(@Valid @RequestBody JobRequest jobRequest) {
        Response response = Response.successResponse(jobService.createJob(jobRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/jobs")
    ResponseEntity<Response> getListJob() {
        Response response = Response.successResponse(jobService.getListJob());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/jobs/{idJob}")
    ResponseEntity<Response> JobDetail(@PathVariable Long idJob) {
        Response response = Response.successResponse(jobService.getJobDetail(idJob));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
