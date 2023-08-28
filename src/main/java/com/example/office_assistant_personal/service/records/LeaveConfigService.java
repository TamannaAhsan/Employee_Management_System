package com.example.office_assistant_personal.service.records;

import com.example.office_assistant_personal.DTO.LeaveTypeDTO;
import com.example.office_assistant_personal.DTO.YearlyLeaveDTO;
import com.example.office_assistant_personal.exception.ApiSystemException;

import java.util.List;

public interface LeaveConfigService {

   LeaveTypeDTO create (LeaveTypeDTO leaveTypeDTO) throws ApiSystemException;

   List<LeaveTypeDTO> get ();

   LeaveTypeDTO getById (Long id) throws ApiSystemException;

   void deleteLeaveType (Long id) throws ApiSystemException;

   LeaveTypeDTO update (LeaveTypeDTO leaveTypeDTO) throws ApiSystemException;

   YearlyLeaveDTO createYearlyLeave (YearlyLeaveDTO yearlyLeaveDTO, Long id) throws ApiSystemException;

   List<YearlyLeaveDTO> getAllLeave ();

   YearlyLeaveDTO getByYearlyLeaveId (Long id) throws ApiSystemException;

   void deleteYearlyLeave (Long id) throws ApiSystemException;

   YearlyLeaveDTO updateYearlyLeave (YearlyLeaveDTO yearlyLeaveDTO) throws ApiSystemException;

}
