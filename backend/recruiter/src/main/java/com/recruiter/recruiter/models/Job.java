package com.recruiter.recruiter.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private Double salary;
    private Instant created_at; //Instant có liên quan đến múi giờ
    private Instant updated_at;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL) //khi cascade all thì lưu job also automatic lưu requirement
    private List<Requirement> requirements;

    @ManyToOne
    @JoinColumn(name="employer_id")
    private User employer;

//    @OneToMany(mappedBy = "jobApplication", cascade = CascadeType.ALL)
//    private List<JobApplication> jobApplications;

}
