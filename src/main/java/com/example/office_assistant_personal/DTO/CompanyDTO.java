package com.example.office_assistant_personal.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String companyName;

    private String companyCode;

    private String country;

    private String city;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String ownerName;

    private String ownerEmail;

    private String ownerContact;


}
