package com.berkay.flashcard.flashcard_study_api.business;

import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import com.berkay.flashcard.flashcard_study_api.entities.Flashcard;

import java.util.List;

public interface FlashcardService {
    List<Flashcard> getFlashcardsByDeck(int deckId);
    List<Flashcard> getFlashcardsByDeckSorted(int deckId);
    List<Flashcard> getFlashcardsByDeck(int deckId, int page, int pageSize);
    Flashcard addFlashcardToDeck(int deckId, Flashcard flashcard);
    Flashcard updateFlashcard(int deckId, int flashcardId, Flashcard flashcard);
    String deleteFlashcard(int flashcardId);
}
