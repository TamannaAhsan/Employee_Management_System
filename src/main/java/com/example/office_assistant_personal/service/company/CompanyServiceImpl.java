package com.example.office_assistant_personal.service.company;

import com.example.office_assistant_personal.DTO.CompanyDTO;
import com.example.office_assistant_personal.DTO.mapper.AutoUserMapper;
import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.helper.AuthorityHelper;
import com.example.office_assistant_personal.repository.company.CompanyRepository;
import com.example.office_assistant_personal.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.office_assistant_personal.utils.Utility.isNotNull;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final AuthorityHelper authorityHelper;

    @Override
    public CompanyDTO create(CompanyDTO companyDTO) throws ApiSystemException {
        try {
            if (isNotNull(companyDTO.getId())) {
                throw new ApiNotAcceptableException("company.id.already.exist");
            }
            companyDTO.setCreatedAt(LocalDateTime.now());
            Company company = AutoUserMapper.MAPPER.mapToCompany(companyDTO);
            Company savedCompany = companyRepository.save(company);
            CompanyDTO savedCompanyDTO = AutoUserMapper.MAPPER.mapToCompanyDTO(savedCompany);
            return savedCompanyDTO;

        }catch (Exception e) {
            throw new ApiSystemException("company.not.created");
        }
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) throws ApiSystemException {
        try{
            Company company = companyRepository.findById(companyDTO.getId())
                    .orElseThrow(()-> new RuntimeException("Company is not found"));

            company.setCompanyCode(companyDTO.getCompanyCode());
            company.setCompanyName(companyDTO.getCompanyName());
            company.setUpdatedAt(LocalDateTime.now());
            company.setCity(companyDTO.getCity());
            company.setCountry(companyDTO.getCountry());
            company.setAddress(companyDTO.getAddress());
            company.setOwnerName(companyDTO.getOwnerName());
            company.setOwnerEmail(companyDTO.getOwnerEmail());
            company.setOwnerContact(companyDTO.getOwnerContact());
            Company updatedCompany = companyRepository.save(company);
            CompanyDTO updatedCompanyDTO = AutoUserMapper.MAPPER.mapToCompanyDTO(updatedCompany);
            return updatedCompanyDTO;

        }catch (Exception e) {
            throw new ApiSystemException("company.not.updated");
        }
    }

    @Override
    public void delete(Long id) throws ApiSystemException {
        try {
            companyRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("company.not.found");
        }
    }

    @Override
    public List<CompanyDTO> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(element-> AutoUserMapper.MAPPER.mapToCompanyDTO(element))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getById(Long id) throws ApiSystemException {
        try {
            Company company = companyRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Company is not found"));
            CompanyDTO companyDTO = AutoUserMapper.MAPPER.mapToCompanyDTO(company);
            return companyDTO;

        }catch (Exception e) {
            throw new ApiSystemException("company.not.found");
        }
    }
}
