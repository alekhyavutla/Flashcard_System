package com.flashcard.flashcardSystem.Repository;

import com.flashcard.flashcardSystem.Model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard,Long> {
    List<Flashcard> findByStudentId(Long studentId);

    @Query("SELECT f FROM Flashcard f WHERE f.studentId = :studentId")
    List<Flashcard> findAllByStudentId(@Param("studentId") Long studentId);
}
