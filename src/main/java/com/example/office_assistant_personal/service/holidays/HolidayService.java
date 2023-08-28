package com.example.office_assistant_personal.service.holidays;

import com.example.office_assistant_personal.DTO.HolidayDTO;

import java.util.Set;

public interface HolidayService {

    HolidayDTO create (HolidayDTO holidayDTO);

    HolidayDTO update (HolidayDTO holidayDTO);

    Set<HolidayDTO> getAll ();

    HolidayDTO getById (Long id);

    void delete (Long id);


}
