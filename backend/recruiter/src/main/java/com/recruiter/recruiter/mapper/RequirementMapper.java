package com.recruiter.recruiter.mapper;

import com.recruiter.recruiter.dto.request.RequirementRequest;
import com.recruiter.recruiter.dto.response.RequirementResponse;
import com.recruiter.recruiter.models.Requirement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequirementMapper {
    RequirementMapper INSTANCE = Mappers.getMapper( RequirementMapper.class );
    Requirement requirementRequestToRequirement(RequirementRequest requirementRequest);
    RequirementResponse requirementToRequirementResponse(Requirement requirement);
}
