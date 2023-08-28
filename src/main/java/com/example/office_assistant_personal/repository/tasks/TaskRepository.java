package com.example.office_assistant_personal.repository.tasks;

import com.example.office_assistant_personal.entity.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
