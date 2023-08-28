package com.example.office_assistant_personal.service.notes;

import com.example.office_assistant_personal.DTO.NoteDTO;

import java.util.List;

public interface NoteService {

    NoteDTO create (NoteDTO noteDTO);

    NoteDTO update (NoteDTO noteDTO);

    List<NoteDTO> getAll ();

    NoteDTO getById (Long id);

    void delete (Long id);


}
