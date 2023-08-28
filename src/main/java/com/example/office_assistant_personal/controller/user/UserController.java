package com.example.office_assistant_personal.controller.user;

import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.user.UserService;
import com.example.office_assistant_personal.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll (){
        List<UserDTO> userDTOList = userService.getAll();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById (@PathVariable Long id) throws ApiSystemException {
        UserDTO userDTO = userService.getById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser (@RequestBody UserDTO userDTO) throws ApiSystemException {
        UserDTO updatedUserDTO = userService.update(userDTO);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> resetPassword (@PathVariable Long id, @RequestBody String password) throws ApiSystemException {
        UserDTO updatedPasswordDTO = userService.resetPassword(id,password);
        return new ResponseEntity<>(updatedPasswordDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete (@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        userService.delete(id);
        Map<String,String> message = Map.of("message", "User deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
