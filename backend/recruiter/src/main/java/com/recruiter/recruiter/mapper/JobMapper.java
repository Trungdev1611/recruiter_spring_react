package com.recruiter.recruiter.mapper;

import com.recruiter.recruiter.dto.request.JobRequest;
import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.models.Job;
import com.recruiter.recruiter.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper( JobMapper.class );
    Job jobRequestToJob(JobRequest jobRequest);
}
