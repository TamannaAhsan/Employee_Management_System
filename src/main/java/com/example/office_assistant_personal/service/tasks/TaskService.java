package com.example.office_assistant_personal.service.tasks;

import com.example.office_assistant_personal.DTO.TaskDTO;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;

import java.util.List;

public interface TaskService {

    TaskDTO create (TaskDTO taskDTO) throws ApiSystemException, ApiNotAcceptableException;

    TaskDTO update (TaskDTO taskDTO) throws ApiSystemException, ApiNotAcceptableException;

    void delete (Long id) throws ApiSystemException;

    List<TaskDTO> get ();

    TaskDTO getById (Long id) throws ApiSystemException;
}
