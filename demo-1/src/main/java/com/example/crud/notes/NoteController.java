package com.example.crud.notes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;
    

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createNote(@RequestBody Note note) {
        return noteService.newNote(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNoteById(@PathVariable Integer id, @RequestBody Note updatedNote) {
        return noteService.updateNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNoteById(@PathVariable Integer id) {
        return noteService.deleteNote(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNoteById(@PathVariable Integer id) {
     
        return this.noteService.getNoteById(id);
    }
    
    @GetMapping
    public List<Note> getAllNotes(){
    	return noteService.getNotes();
    }
    
    

}
