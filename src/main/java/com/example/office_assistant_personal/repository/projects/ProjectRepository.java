package com.example.office_assistant_personal.repository.projects;

import com.example.office_assistant_personal.entity.projects.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
