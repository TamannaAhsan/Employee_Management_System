package com.example.office_assistant_personal.repository.records;

import com.example.office_assistant_personal.entity.records.YearlyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyLeaveRepository extends JpaRepository<YearlyLeave, Long> {
}
