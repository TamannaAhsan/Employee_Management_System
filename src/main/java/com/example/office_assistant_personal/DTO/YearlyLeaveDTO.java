package com.example.office_assistant_personal.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YearlyLeaveDTO {

    private Long id;

    private Integer year;

    private Integer maximumDay;

    private Integer minimumDay;

    private Integer leaveTypeId;
}
