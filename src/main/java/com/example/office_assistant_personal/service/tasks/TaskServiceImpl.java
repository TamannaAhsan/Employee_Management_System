package com.example.office_assistant_personal.service.tasks;

import com.example.office_assistant_personal.DTO.TaskDTO;
import com.example.office_assistant_personal.DTO.mapper.AutoUserMapper;
import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.entity.projects.Project;
import com.example.office_assistant_personal.entity.tasks.Task;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.repository.company.CompanyRepository;
import com.example.office_assistant_personal.repository.projects.ProjectRepository;
import com.example.office_assistant_personal.repository.tasks.TaskRepository;
import com.example.office_assistant_personal.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.office_assistant_personal.utils.Utility.isNotNull;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    @Override
    public TaskDTO create(TaskDTO taskDTO) throws ApiSystemException, ApiNotAcceptableException {
        try {
            if (isNotNull(taskRepository.findById(taskDTO.getId()))) {
                throw new ApiNotAcceptableException("task.id.already.exist");
            }
            Company company = companyRepository.findById(taskDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));
            User user = userRepository.findById(taskDTO.getAssignedTo().getId())
                    .orElseThrow(()->new ApiSystemException("User is not found"));
            Project project = projectRepository.findById(taskDTO.getProject().getId())
                    .orElseThrow(()->new ApiSystemException("Project is not found"));
            Task task = AutoUserMapper.MAPPER.mapToTask(taskDTO);
            task.setCreatedAt(LocalDateTime.now());
            task.setTaskName(taskDTO.getTaskName());
            task.setDetails(taskDTO.getDetails());
            task.setAssignedTo(user);
            task.setProject(project);
            task.setCompany(company);
            Task savedTask = taskRepository.save(task);
            TaskDTO savedTaskDTO = AutoUserMapper.MAPPER.mapToTaskDTO(savedTask);
            return savedTaskDTO;
        }catch (Exception e) {
            throw new ApiSystemException("task.not.created");
        }
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) throws ApiSystemException {
        try {
            Task task = taskRepository.findById(taskDTO.getId())
                    .orElseThrow(()->new ApiSystemException("Task is not found"));
            Company company = companyRepository.findById(taskDTO.getCompany().getId())
                    .orElseThrow(()->new ApiSystemException("Company is not found"));
            User user = userRepository.findById(taskDTO.getAssignedTo().getId())
                    .orElseThrow(()->new ApiSystemException("User is not found"));
            Project project = projectRepository.findById(taskDTO.getProject().getId())
                    .orElseThrow(()->new ApiSystemException("Project is not found"));
            task.setUpdatedAt(LocalDateTime.now());
            task.setTaskName(taskDTO.getTaskName());
            task.setDetails(taskDTO.getDetails());
            task.setAssignedTo(user);
            task.setProject(project);
            task.setCompany(company);
            Task savedTask = taskRepository.save(task);
            TaskDTO updatedTaskDTO = AutoUserMapper.MAPPER.mapToTaskDTO(savedTask);
            return updatedTaskDTO;
        }catch (Exception e) {
            throw new ApiSystemException("task.not.found");
        }
    }

    @Override
    public void delete(Long id) throws ApiSystemException {
        try {
            taskRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("task.not.found");
        }
    }

    @Override
    public List<TaskDTO> get() {
        List<Task> taskList = taskRepository.findAll();
        return taskList.stream()
                .map(element-> AutoUserMapper.MAPPER.mapToTaskDTO(element))
                .collect(Collectors.toList());
    }


    @Override
    public TaskDTO getById(Long id) throws ApiSystemException {
        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(()->new ApiSystemException("task is not found"));
            TaskDTO taskDTO = AutoUserMapper.MAPPER.mapToTaskDTO(task);
            return taskDTO;
        }catch (Exception e) {
            throw new ApiSystemException("task.not.found");
        }
    }
}
