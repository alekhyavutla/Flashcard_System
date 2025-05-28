package com.flashcard.flashcardSystem.Controller;


import com.flashcard.flashcardSystem.Model.Flashcard;
import com.flashcard.flashcardSystem.Service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    @Autowired
    private FlashcardService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> addFlashcard(@RequestBody Flashcard flashcard) {
        Flashcard saved = service.addFlashcard(flashcard);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Flashcard added successfully");
        response.put("subject", saved.getSubject());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-subject")
    public ResponseEntity<List<Flashcard>> getMixedSubjectFlashcards(
            @RequestParam("student_id") Long studentId,
            @RequestParam("limit") int limit) {

        List<Flashcard> flashcards = service.getMixedSubjectFlashcards(studentId, limit);
        return ResponseEntity.ok(flashcards);
    }



}