package com.recruiter.recruiter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequirementResponse {
    private Long id;
    private String contentRequire;

    @Override
    public String toString() {
        return "RequirementResponse{id=" + id + ", description='" + contentRequire + "'}";
    }
}
