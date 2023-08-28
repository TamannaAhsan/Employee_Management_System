package com.example.office_assistant_personal.DTO;

import com.example.office_assistant_personal.entity.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveApplicationDTO {

    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String remark;

    private String managerRemark;

    private LeaveStatus leaveStatus;

    private LeaveTypeDTO leaveTypeDTO;

    private UserDTO userDTO;




}
