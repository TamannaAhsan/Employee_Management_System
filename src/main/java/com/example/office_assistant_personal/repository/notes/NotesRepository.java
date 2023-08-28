package com.example.office_assistant_personal.repository.notes;

import com.example.office_assistant_personal.entity.notes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
}
