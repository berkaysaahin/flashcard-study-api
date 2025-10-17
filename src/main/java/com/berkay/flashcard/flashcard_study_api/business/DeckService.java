package com.berkay.flashcard.flashcard_study_api.business;

import com.berkay.flashcard.flashcard_study_api.entities.Deck;

import java.util.List;

public interface DeckService {

    List<Deck> getAll();

    List<Deck> getAllSorted();

    List<Deck> getAll(int page, int pageSize);

    String add(Deck deck);

    Deck getDeckByTitle(String title);

    Void deleteById(String title);
}
