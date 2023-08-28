package com.example.office_assistant_personal.DTO;

import com.example.office_assistant_personal.entity.enums.RoleStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String contact;

    private String address;

    private String designation;

    private String religion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CompanyDTO company;

    private String token;

    private LocalDate tokenExpireDate;

    private RoleStatus roleStatus;

}
