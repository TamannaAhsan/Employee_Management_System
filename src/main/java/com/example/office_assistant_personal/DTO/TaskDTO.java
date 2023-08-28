package com.example.office_assistant_personal.DTO;

import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.entity.projects.Project;
import com.example.office_assistant_personal.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String taskName;

    private String details;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private Project project;

    private Company company;

    private User assignedTo;
}
