package com.example.office_assistant_personal.controller.projects;

import com.example.office_assistant_personal.DTO.ProjectDTO;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.projects.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> create (@RequestBody ProjectDTO projectDTO) throws ApiSystemException, ApiNotAcceptableException {
        ProjectDTO savedProjectDTO = projectService.create(projectDTO);
        return new ResponseEntity<>(savedProjectDTO, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAll (){
        List<ProjectDTO> projectDTOList = projectService.get();
        return new ResponseEntity<>(projectDTOList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById (@PathVariable Long id) throws ApiSystemException {
        ProjectDTO projectDTO = projectService.getById(id);
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete (@PathVariable Long id) throws ApiSystemException {
        projectService.deleteById(id);
        Map<String,String> message = Map.of("message", "Project deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<ProjectDTO> update (@RequestBody ProjectDTO projectDTO) throws ApiSystemException {
        ProjectDTO updatedProjectDTO = projectService.update(projectDTO);
        return new ResponseEntity<>(updatedProjectDTO, HttpStatus.OK);
    }


}
