package com.berkay.flashcard.flashcard_study_api.dataAccess;

import com.berkay.flashcard.flashcard_study_api.entities.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardDao extends JpaRepository<Flashcard, Integer> {

    Flashcard getFlashcardByFlashcardId(int flashcardId);

    Flashcard getFlashcardByQuestion(String question);

    List<Flashcard> getFlashcardsByDeck_DeckId(int deckId);
}
