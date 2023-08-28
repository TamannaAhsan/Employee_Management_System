package com.example.office_assistant_personal.DTO.mapper;

import com.example.office_assistant_personal.DTO.CompanyDTO;
import com.example.office_assistant_personal.DTO.HolidayDTO;
import com.example.office_assistant_personal.DTO.LeaveApplicationDTO;
import com.example.office_assistant_personal.DTO.LeaveTypeDTO;
import com.example.office_assistant_personal.DTO.NoteDTO;
import com.example.office_assistant_personal.DTO.ProjectDTO;
import com.example.office_assistant_personal.DTO.TaskDTO;
import com.example.office_assistant_personal.DTO.UserDTO;
import com.example.office_assistant_personal.DTO.YearlyLeaveDTO;
import com.example.office_assistant_personal.entity.company.Company;
import com.example.office_assistant_personal.entity.holidays.Holiday;
import com.example.office_assistant_personal.entity.notes.Note;
import com.example.office_assistant_personal.entity.projects.Project;
import com.example.office_assistant_personal.entity.records.LeaveApplication;
import com.example.office_assistant_personal.entity.records.LeaveType;
import com.example.office_assistant_personal.entity.tasks.Task;
import com.example.office_assistant_personal.entity.user.User;
import com.example.office_assistant_personal.entity.records.YearlyLeave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDTO mapToUserDTO (User user);

    User mapToUser(UserDTO userDto);

    CompanyDTO mapToCompanyDTO(Company company);

    Company mapToCompany (CompanyDTO companyDTO);

    YearlyLeaveDTO mapToYearlyLeaveDTO (YearlyLeave yearlyLeave);

    YearlyLeave mapToYearlyLeave (YearlyLeaveDTO yearlyLeaveDTO);

    LeaveTypeDTO mapToLeaveTypeDTO (LeaveType leaveType);

    LeaveType mapToLeaveType (LeaveTypeDTO leaveTypeDTO);

    LeaveApplicationDTO mapToLeaveApplicationDTO (LeaveApplication leaveApplication);

    LeaveApplication mapToLeaveApplication (LeaveApplicationDTO leaveApplicationDTO);

    Project mapToProject (ProjectDTO projectDTO);

    ProjectDTO mapToProjectDTO (Project project);

    Task mapToTask (TaskDTO taskDTO);

    TaskDTO mapToTaskDTO (Task task);

    HolidayDTO mapToHolidayDTO (Holiday holiday);

    Holiday mapToHoliday (HolidayDTO holidayDTO);

    NoteDTO mapToDTO (Note note);

    Note mapToNote (NoteDTO notesDTO);

}
