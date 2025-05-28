package com.flashcard.flashcardSystem.Service;

import com.flashcard.flashcardSystem.Model.Flashcard;
import com.flashcard.flashcardSystem.Repository.FlashcardRepository;
import com.flashcard.flashcardSystem.RuleBased.SubjectClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlashcardService {
    @Autowired                          // field injection to make code simple
    private FlashcardRepository repository;

    @Autowired
    private SubjectClassifier classifier;
    public Flashcard addFlashcard(Flashcard flashcard) {

        String subject = classifier.detectSubject(flashcard.getQuestion(), flashcard.getAnswer());

        flashcard.setSubject(subject);

        return repository.save(flashcard);
    }


    public List<Flashcard> getMixedSubjectFlashcards(Long studentId, int limit) {
        List<Flashcard> allCards = repository.findAllByStudentId(studentId);

        Map<String, List<Flashcard>> subjectMap = allCards.stream()
                .collect(Collectors.groupingBy(Flashcard::getSubject));

        List<String> subjects = new ArrayList<>(subjectMap.keySet());
        Collections.shuffle(subjects);

        List<Flashcard> result = new ArrayList<>();
        for (String subject : subjects) {
            List<Flashcard> cards = subjectMap.get(subject);
            Collections.shuffle(cards);
            for (Flashcard card : cards) {
                if (result.size() < limit) {
                    result.add(card);
                } else {
                    return result;
                }
            }
        }
        return result;
    }


}
