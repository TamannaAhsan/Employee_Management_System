package com.example.office_assistant_personal.controller.tasks;

import com.example.office_assistant_personal.DTO.TaskDTO;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.tasks.TaskService;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> create (@RequestBody TaskDTO taskDTO) throws ApiSystemException, ApiNotAcceptableException {
        TaskDTO savedTaskDTO = taskService.create(taskDTO);
        return new ResponseEntity<>(savedTaskDTO, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<TaskDTO>> getAll (){
        List<TaskDTO> taskListDTO = taskService.get();
        return new ResponseEntity<>(taskListDTO,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById (@PathVariable Long id) throws ApiSystemException {
        TaskDTO taskDTO = taskService.getById(id);
        return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete (@PathVariable Long id) throws ApiSystemException {
        taskService.delete(id);
        Map<String,String> message = Map.of("message", "Project deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<TaskDTO> update (@RequestBody TaskDTO taskDTO) throws ApiSystemException, ApiNotAcceptableException {
        TaskDTO updatedTaskDTO = taskService.update(taskDTO);
        return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
    }
}
