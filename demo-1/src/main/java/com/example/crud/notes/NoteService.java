package com.example.crud.notes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ResponseEntity<Object> newNote(Note note) {
        Note savedNote = noteRepository.save(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    public List<Note> getNotes() {
        return this.noteRepository.findAll();
    }

    public ResponseEntity<Object> updateNote(Integer id, Note updatedNote) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }

        Note existingNote = noteOptional.get();
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        noteRepository.save(existingNote);

        return ResponseEntity.ok(existingNote);
    }

    public ResponseEntity<Object> deleteNote(Integer id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        System.out.println("Querying note with ID: " + id);

        if (noteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }

        noteRepository.deleteById(id);

        return ResponseEntity.ok("Note deleted successfully");
    }

    public ResponseEntity<Object> getNoteById(Integer id) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
        	System.out.println("No note found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        System.out.println("Note found: " + noteOptional.get());
        return ResponseEntity.ok(noteOptional.get());
    }
}
