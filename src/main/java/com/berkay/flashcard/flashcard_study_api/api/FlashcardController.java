package com.berkay.flashcard.flashcard_study_api.api;

import com.berkay.flashcard.flashcard_study_api.business.FlashcardService;
import com.berkay.flashcard.flashcard_study_api.entities.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/decks")
@RestController
@CrossOrigin
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping(value = "/{deckId}/flashcards")
    public ResponseEntity<List<Flashcard>> getFlashcards(@PathVariable("deckId")  int deckId) {
        List<Flashcard> flashcards = this.flashcardService.getFlashcardsByDeck(deckId);
        return !flashcards.isEmpty() ? ResponseEntity.ok(flashcards) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{deckId}/flashcardsSorted")
    public List<Flashcard> getFlashcardsSorted(@PathVariable("deckId")  int deckId) {
        return flashcardService.getFlashcardsByDeckSorted(deckId);
    }

    @GetMapping(value = "/{deckId}/flashcards/getAllByPage")
    public List<Flashcard> getAllByPage(@PathVariable("deckId")  int deckId, @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return flashcardService.getFlashcardsByDeck(deckId, page, pageSize);
    }

    @PostMapping(value = "/{deckId}/flashcards/add" )
    public ResponseEntity<Flashcard> add( @PathVariable("deckId")  int deckId, @RequestBody Flashcard flashcard) {
         Flashcard saved = flashcardService.addFlashcardToDeck(deckId, flashcard);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(value = "/{deckId}/flashcards/{flashcardId}")
    public ResponseEntity<Flashcard> update(@PathVariable("deckId") int deckId, @PathVariable("flashcardId") int flashcardId, @RequestBody Flashcard flashcard) {
        Flashcard resultFlashcard = flashcardService.updateFlashcard(deckId, flashcardId, flashcard);
        if (resultFlashcard != null) {
            return new ResponseEntity<>(resultFlashcard, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{deckId}/flashcards/{flashcardId}")
    public ResponseEntity<String> delete(@PathVariable("deckId") int deckId,  @PathVariable("flashcardId") int flashcardId) {
        String result = flashcardService.deleteFlashcard(flashcardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }






}
