package com.example.office_assistant_personal.auth;

import com.example.office_assistant_personal.DTO.Response;
import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.component.BaseComponent;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.user.UserService;
import com.example.office_assistant_personal.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/auth")
@RequiredArgsConstructor
public class AuthenticationController extends BaseComponent {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final AuthenticationService authenticationService;


  @PostMapping("/employee")
  public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) throws ApiNotAcceptableException, ApiSystemException {
    return ResponseEntity.ok(authenticationService.register(userDTO));
  }

  @PostMapping("/admin")
  public ResponseEntity<UserDTO> registerAdmin(@RequestBody UserDTO userDTO) throws ApiNotAcceptableException, ApiSystemException {
    return ResponseEntity.ok(authenticationService.registerAdmin(userDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(@Context HttpServletRequest requestContext,
                                      @Context HttpServletResponse responseContext) throws Exception {
    try {
      String token = requestContext.getHeader("Authorization");

      logger.info("user AccessToken  :" + token);
      logger.info("Start logout Process :" + token);
      User securityContextUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (token == null || token.equals("")) {
        logger.info("Logging out the user , no accessToken provided :" + token);
        return ResponseUtil.buildResponse(Response.<Void>builder()
                .data(null)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(getMessage("operation.not.successful"))
                .build());
      }

      if (!authenticationService.logoutByToken(securityContextUser)) {
        logger.info("Logging out the user , no valid auth accessToken provided :" + token);
        return ResponseUtil.buildResponse(Response.<Void>builder()
                .data(null)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(getMessage("operation.not.successful"))
                .build());
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
        HttpSession session = requestContext.getSession(false);

        if (session != null)
          session.invalidate();
      }
      logger.info("Logout is successful.");
      return ResponseUtil.buildResponse(Response.<Void>builder()
              .data(null)
              .message(getMessage("logout.successful"))
              .build());
    } catch (Exception e) {
      logger.error("Logging out the user , no auth accessToken provided :" + e);
    }
    return ResponseUtil.buildResponse(Response.<Void>builder()
            .data(null)
            .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(getMessage("operation.not.successful"))
            .build());
  }


}
