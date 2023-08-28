package com.example.office_assistant_personal.repository.holidays;

import com.example.office_assistant_personal.entity.holidays.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
