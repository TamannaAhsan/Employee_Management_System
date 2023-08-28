package com.example.office_assistant_personal.controller.company;

import com.example.office_assistant_personal.DTO.CompanyDTO;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDTO> create (@RequestBody CompanyDTO companyDTO) throws ApiSystemException {
        CompanyDTO savedCompanyDTO = companyService.create(companyDTO);
        return new ResponseEntity<>(savedCompanyDTO, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> getAll (){
        List<CompanyDTO> companyDTOList = companyService.getAll();
        return new ResponseEntity<>(companyDTOList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById (@PathVariable Long id) throws ApiSystemException {
        CompanyDTO companyDTO = companyService.getById(id);
        return new ResponseEntity<>(companyDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete (@PathVariable Long id) throws ApiSystemException {
        companyService.delete(id);
        Map<String,String> message = Map.of("message", "Company deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<CompanyDTO> update (@RequestBody CompanyDTO companyDTO) throws ApiSystemException {
        CompanyDTO updatedCompany = companyService.update(companyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }
}
