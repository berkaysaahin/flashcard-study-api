package com.berkay.flashcard.flashcard_study_api.business;

import com.berkay.flashcard.flashcard_study_api.dataAccess.DeckDao;
import com.berkay.flashcard.flashcard_study_api.dataAccess.FlashcardDao;
import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import com.berkay.flashcard.flashcard_study_api.entities.Flashcard;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardManager implements FlashcardService {
    private final FlashcardDao flashcardDao;
    private final DeckDao deckDao;

    @Autowired
    public FlashcardManager(FlashcardDao flashcardDao, DeckDao deckDao) {
        this.flashcardDao = flashcardDao;
        this.deckDao = deckDao;
    }

    @Override
    public List<Flashcard> getFlashcardsByDeck(int deckId) {
        return this.flashcardDao.getFlashcardsByDeck_DeckId(deckId);
    }

    @Override
    public List<Flashcard> getFlashcardsByDeckSorted(int deckId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "question");
        return this.flashcardDao.getFlashcardsByDeck_DeckId(deckId, sort);
    }

    @Override
    public List<Flashcard> getFlashcardsByDeck(int deckId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Flashcard> result = this.flashcardDao.getFlashcardsByDeck_DeckId(deckId, pageable);
        return result.getContent();
    }

    @Override
    public Flashcard addFlashcardToDeck(int deckId, Flashcard flashcard) {
        Deck deck = deckDao.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));
        flashcard.setDeck(deck);

        return this.flashcardDao.save(flashcard);
    }

    @Override
    @Transactional
    public Flashcard updateFlashcard(int deckId, int flashcardId, Flashcard updatedFlashcard) {

        return this.flashcardDao.findById(flashcardId).filter(flashcard -> flashcard.getDeck().getDeckId() == deckId).map(
                flashcard -> {
                    if (updatedFlashcard.getQuestion() != null) {
                        flashcard.setQuestion(updatedFlashcard.getQuestion());
                    }
                    if (updatedFlashcard.getAnswer() != null) {
                        flashcard.setAnswer(updatedFlashcard.getAnswer());
                    }
                    return this.flashcardDao.save(flashcard);
                }
        ).orElse(null);
    }

    @Override
    @Transactional
    public String deleteFlashcard(int flashcardId) {
        if(!flashcardDao.existsById(flashcardId)) {
            return "Flashcard not found";
        }
        this.flashcardDao.deleteById(flashcardId);
        return  "flashcard deleted successfully";
    }
}
