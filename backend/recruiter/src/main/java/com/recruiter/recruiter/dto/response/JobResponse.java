package com.recruiter.recruiter.dto.response;

import com.recruiter.recruiter.dto.request.RequirementRequest;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class JobResponse {
    private Long id;
    private String jobTitle;

    private String jobDescription;
    private String location;

    private Double salary;
    private List<RequirementResponse> requirementResponses;
}
