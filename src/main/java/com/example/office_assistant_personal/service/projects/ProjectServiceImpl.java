package com.example.office_assistant_personal.service.projects;

import com.example.office_assistant_personal.DTO.ProjectDTO;
import com.example.office_assistant_personal.DTO.mapper.AutoUserMapper;
import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.entity.projects.Project;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.repository.company.CompanyRepository;
import com.example.office_assistant_personal.repository.projects.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.office_assistant_personal.utils.Utility.isNotNull;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    @Override
    public ProjectDTO create(ProjectDTO projectDTO) throws ApiSystemException, ApiNotAcceptableException {
        try {
            if (isNotNull(projectRepository.findById(projectDTO.getId()))) {
                throw new ApiNotAcceptableException("project.id.already.exist");
            }
            Company company = companyRepository.findById(projectDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));

            Project project = AutoUserMapper.MAPPER.mapToProject(projectDTO);
            project.setDescription(projectDTO.getDescription());
            project.setProjectName(projectDTO.getProjectName());
            project.setCreatedAt(LocalDateTime.now());
            project.setUser(projectDTO.getUser());
            project.setCompany(company);
            projectRepository.save(project);
            ProjectDTO savedProject = AutoUserMapper.MAPPER.mapToProjectDTO(project);
            return savedProject;
        }catch (Exception e) {
            throw new ApiSystemException("project.not.created");
        }
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) throws ApiSystemException {
        try {
            Project project = projectRepository.findById(projectDTO.getId())
                    .orElseThrow(()-> new ApiSystemException("Project is not found"));

            Company company = companyRepository.findById(projectDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));

            project.setDescription(projectDTO.getDescription());
            project.setProjectName(projectDTO.getProjectName());
            project.setCreatedAt(LocalDateTime.now());
            project.setUser(projectDTO.getUser());
            project.setCompany(company);
            projectRepository.save(project);
            projectRepository.save(project);
            ProjectDTO updatedProject = AutoUserMapper.MAPPER.mapToProjectDTO(project);
            return updatedProject;
        }catch (Exception e) {
            throw new ApiSystemException("project.not.found");
        }
    }

    @Override
    public ProjectDTO getById(Long id) throws ApiSystemException {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(()->new ApiSystemException("Project is not found"));
            ProjectDTO projectDTO = AutoUserMapper.MAPPER.mapToProjectDTO(project);
            return projectDTO;
        }catch (Exception e) {
            throw new ApiSystemException("project.not.found");
        }
    }

    @Override
    public List<ProjectDTO> get() {
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(element-> AutoUserMapper.MAPPER.mapToProjectDTO(element))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) throws ApiSystemException {
        try {
            projectRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("project.not.found");
        }
    }
}
