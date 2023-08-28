package com.example.office_assistant_personal.auth;

import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.config.security.JwtService;
import com.example.office_assistant_personal.entity.enums.RoleStatus;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.repository.user.UserRepository;
import com.example.office_assistant_personal.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public UserDTO register(UserDTO userDTO) throws ApiNotAcceptableException, ApiSystemException {
    userDTO.setRoleStatus(RoleStatus.EMPLOYEE);
    return userService.create(userDTO);
  }

  public UserDTO registerAdmin(UserDTO userDTO) throws ApiNotAcceptableException, ApiSystemException {
    userDTO.setRoleStatus(RoleStatus.ADMIN);
    return userService.createAdmin(userDTO);
  }
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    user.setToken(jwtToken);

    Date expirationDate = jwtService.extractExpiration(jwtToken);
    LocalDate expireDate = LocalDate.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());
    user.setTokenExpireDate(expireDate);

    userRepository.save(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public Boolean logoutByToken(User user) throws Exception {
    try {
      logger.debug(">> logout()");
      logger.info("Retrieving user by session accessToken");
      if (user != null) {
        user.setLogoutDateTime(LocalDateTime.now());
        user.setTokenExpireDate(null);
        user.setUserLogged(false);
        user.setToken("");
        logger.info("Updating user.");
        User logoutUser = userService.updateUser(user);
        logger.info("User Updated an logout");
        return (logoutUser == null ? false : true);
      }
    } catch (Exception e) {
      logger.error("Logging out the user , no auth accessToken provided :" + e);
    }
    return false;
  }
}
