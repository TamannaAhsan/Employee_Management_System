package com.example.office_assistant_personal.helper;

import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorityHelper {
    private final UserRepository userRepository;

    public Optional<User> getCurrentLoggedInUser () throws ApiSystemException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(userRepository.findByEmail(user.getEmail()))
                .orElseThrow(()-> new ApiSystemException("User is not Found"));
    }
}
