package com.example.office_assistant_personal.controller.records;

import com.example.office_assistant_personal.DTO.LeaveTypeDTO;
import com.example.office_assistant_personal.DTO.YearlyLeaveDTO;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.service.records.LeaveConfigService;
import com.example.office_assistant_personal.utils.Utility;
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
@RequestMapping("api/v1/leaveConfig")
@RequiredArgsConstructor
public class LeaveConfigController {
    private final LeaveConfigService leaveConfigService;

    @PostMapping
    public ResponseEntity<LeaveTypeDTO> create (@RequestBody LeaveTypeDTO leaveTypeDTO) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        LeaveTypeDTO savedLeaveTypeDTO = leaveConfigService.create(leaveTypeDTO);
        return new ResponseEntity<>(savedLeaveTypeDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<LeaveTypeDTO>> get () throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        List<LeaveTypeDTO> leaveTypes = leaveConfigService.get();
        return new ResponseEntity<>(leaveTypes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveTypeDTO> getById (@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        LeaveTypeDTO leaveTypeDTO = leaveConfigService.getById(id);
        return new ResponseEntity<>(leaveTypeDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LeaveTypeDTO> update (@RequestBody LeaveTypeDTO leaveTypeDTO) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        LeaveTypeDTO updatedLeaveTypeDTO = leaveConfigService.update(leaveTypeDTO);
        return new ResponseEntity<>(updatedLeaveTypeDTO, HttpStatus.OK);
    }

    @DeleteMapping("leaveType/delete/{id}")
    public ResponseEntity<Map<String, String>> delete (@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        leaveConfigService.deleteLeaveType(id);
        Map<String,String> message = Map.of("message", "Leave Type deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<YearlyLeaveDTO> createYearlyLeave (@RequestBody YearlyLeaveDTO yearlyLeaveDTO,@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        YearlyLeaveDTO savedYearlyLeaveDTO = leaveConfigService.createYearlyLeave(yearlyLeaveDTO, id);
        return new ResponseEntity<>(savedYearlyLeaveDTO, HttpStatus.CREATED);
    }

    @GetMapping("/yearlyLeave")
    public ResponseEntity<List<YearlyLeaveDTO>> getAllLeave () throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        List<YearlyLeaveDTO> yearlyLeaveDTOS = leaveConfigService.getAllLeave();
        return new ResponseEntity<>(yearlyLeaveDTOS,HttpStatus.OK);
    }

    @DeleteMapping("yearlyLeave/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteYearlyLeave (@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        leaveConfigService.deleteYearlyLeave(id);
        Map<String,String> message = Map.of("message", "Yearly Leave deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("yearlyLeave/{id}")
    public ResponseEntity<YearlyLeaveDTO> getByYearlyLeaveId (@PathVariable Long id) throws ApiSystemException {
        Utility.checkIfUserIsAdmin();
        YearlyLeaveDTO yearlyLeaveDTO = leaveConfigService.getByYearlyLeaveId(id);
        return new ResponseEntity<>(yearlyLeaveDTO,HttpStatus.OK);
    }

  @PutMapping("yearlyLeave")
    public ResponseEntity<YearlyLeaveDTO> updateYearlyLeave (@RequestBody YearlyLeaveDTO yearlyLeaveDTO) throws ApiSystemException {
      Utility.checkIfUserIsAdmin();
      YearlyLeaveDTO updatedYearlyLeaveDTO = leaveConfigService.updateYearlyLeave(yearlyLeaveDTO);
      return new ResponseEntity<>(updatedYearlyLeaveDTO,HttpStatus.OK);
    }

}

