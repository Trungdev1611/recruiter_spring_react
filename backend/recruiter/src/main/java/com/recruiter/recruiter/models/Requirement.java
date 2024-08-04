package com.recruiter.recruiter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Requirement {
    @Id
    private Long id;
    private String contentRequire;

    @ManyToOne
    @JoinColumn(name = "id_job")
    private Job job;

}
