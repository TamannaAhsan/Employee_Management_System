package com.example.office_assistant_personal.service.user;

import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;

import java.util.List;

public interface UserService {

    UserDTO createAdmin (UserDTO userDTO) throws ApiSystemException, ApiNotAcceptableException;
    void delete (Long id) throws ApiSystemException;

    List<UserDTO> getAll ();

    UserDTO getById (Long id) throws ApiSystemException;

    User updateUser(User user) throws ApiSystemException, ApiNotAcceptableException;

    UserDTO update (UserDTO userDTO) throws ApiSystemException;

    UserDTO resetPassword (Long id, String password) throws ApiSystemException;

    UserDTO create (UserDTO userDTO) throws ApiSystemException, ApiNotAcceptableException;

}
