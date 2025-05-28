package com.flashcard.flashcardSystem.RuleBased;

import org.springframework.stereotype.Component;

@Component
public class SubjectClassifier {

    public String detectSubject(String question, String answer) {
        String text = (question + " " + answer).toLowerCase();

        if (text.contains("photosynthesis") || text.contains("cell") || text.contains("plant"))
            return "Biology";

        if (text.contains("newton") || text.contains("force") || text.contains("acceleration"))
            return "Physics";

        if (text.contains("integral") || text.contains("algebra") || text.contains("equation") || text.contains("calculus"))
            return "Math";

        if (text.contains("war") || text.contains("president") || text.contains("revolution") || text.contains("empire"))
            return "History";

        if(text.contains("atom") || text.contains("molecule") || text.contains("reaction") ||
                text.contains("element") || text.contains("compound") || text.contains("chemical"))
            return "Chemistry";

        return "General One"; // default subject if no match found
    }
}