package com.recruiter.recruiter.dto.request;

import com.recruiter.recruiter.models.Requirement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class JobRequest {
    private Long id;
    @NotNull(message = "Title job is required")
    private String jobTitle;

    private String jobDescription;
    private String location;

    @NotNull(message = "Salary for this job is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be greater than or equal to 0")
    private Double salary;

    @NotNull(message = "Requirement job is required")
    private List<RequirementRequest> requirementsRequests;
}
