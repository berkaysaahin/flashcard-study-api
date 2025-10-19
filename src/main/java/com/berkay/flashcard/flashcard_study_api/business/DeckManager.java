package com.berkay.flashcard.flashcard_study_api.business;

import com.berkay.flashcard.flashcard_study_api.dataAccess.DeckDao;
import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckManager implements DeckService {

    private final DeckDao deckDao;

    @Autowired
    public DeckManager(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    @Override
    public List<Deck> getAll() {
        return this.deckDao.findAll();

    }

    @Override
    public List<Deck> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "title");
        return this.deckDao.findAll(sort);
    }

    @Override
    public List<Deck> getAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page -1, pageSize);
        return this.deckDao.findAll(pageable).getContent();
    }

    @Override
    public String add(Deck deck) {
        this.deckDao.save(deck);
        return "Deck added successfully";
    }

    @Override
    public Deck getDeckByTitle(String title) {
        return this.deckDao.getDeckByTitle(title);
    }

    @Override
    @Transactional
    public Deck updateDeck(Deck updatedDeck){

        return this.deckDao.findById(updatedDeck.getDeckId()).map(
                deck -> {
                    deck.setTitle(updatedDeck.getTitle());
                    deck.setDescription(updatedDeck.getDescription());
                    return deckDao.save(deck);
                }
        ).orElse(null);

    }

    @Override
    @Transactional //if something happens in the process line all changes in database are enrolled back
    public String deleteDeckById(int deckId) {
        if (!deckDao.existsById(deckId)) {
            return "Deck not found";
        }
        this.deckDao.deleteById(deckId);
        return "Deck deleted successfully";
    }

}
