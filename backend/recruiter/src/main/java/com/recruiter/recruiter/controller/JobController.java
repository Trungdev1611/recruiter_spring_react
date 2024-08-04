package com.recruiter.recruiter.controller;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.services.JobService;
import com.recruiter.recruiter.utils.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private JobService jobService;

    @PostMapping("list-job")
    ResponseEntity<Response> createJob(@Valid @RequestBody JobRequest userRequest) {
        Response response = Response.successResponse(jobService.createJob(userRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
