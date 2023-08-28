package com.example.office_assistant_personal.repository.records;

import com.example.office_assistant_personal.entity.records.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
