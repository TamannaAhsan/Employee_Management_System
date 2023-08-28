package com.example.office_assistant_personal.DTO;

import com.example.office_assistant_personal.entity.enums.HolidayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDTO {

    private Long id;

    private String holidayTitle;

    private LocalDate holidayDate;

    private HolidayType holidayType;
}
