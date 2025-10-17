package com.berkay.flashcard.flashcard_study_api.dataAccess;

import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckDao extends JpaRepository<Deck, Integer> {

    Deck getDeckByDeckId(int deckId);
    Deck getDeckByTitle(String title);
    Deck getDeckByDescription(String description);
}
