package com.example.office_assistant_personal.DTO;

import com.example.office_assistant_personal.entity.company.Company;
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
public class ProjectDTO {

    private Long id;

    private String projectName;

    private String description;

    private Company company;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private User user;

}
