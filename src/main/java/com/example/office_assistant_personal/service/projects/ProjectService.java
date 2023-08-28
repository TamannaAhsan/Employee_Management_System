package com.example.office_assistant_personal.service.projects;

import com.example.office_assistant_personal.DTO.ProjectDTO;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;

import java.util.List;

public interface ProjectService {

    ProjectDTO create (ProjectDTO projectDTO) throws ApiSystemException, ApiNotAcceptableException;

    ProjectDTO update (ProjectDTO projectDTO) throws ApiSystemException;

    ProjectDTO getById (Long id) throws ApiSystemException;

    List<ProjectDTO> get ();

    void deleteById (Long id) throws ApiSystemException;
}
