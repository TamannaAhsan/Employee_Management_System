package com.example.office_assistant_personal.service.company;

import com.example.office_assistant_personal.DTO.CompanyDTO;
import com.example.office_assistant_personal.exception.ApiSystemException;

import java.util.List;

public interface CompanyService {

    CompanyDTO create (CompanyDTO companyDTO) throws ApiSystemException;

    CompanyDTO update (CompanyDTO companyDTO) throws ApiSystemException;

    void delete (Long id) throws ApiSystemException;

    List<CompanyDTO> getAll ();

    CompanyDTO getById (Long id) throws ApiSystemException;
}
