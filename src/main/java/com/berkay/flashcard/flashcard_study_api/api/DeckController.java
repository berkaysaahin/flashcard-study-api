package com.berkay.flashcard.flashcard_study_api.api;

import com.berkay.flashcard.flashcard_study_api.business.DeckService;
import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/decks")
@RestController
@CrossOrigin
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("/getAll")
    public List<Deck> getAll() {
        return deckService.getAll();
    }

    @GetMapping(value = "/getAllSorted")
    public List<Deck> getAllSorted() {
        return deckService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public List<Deck> getAll(int page, int pageSize) {
        return deckService.getAll(page, pageSize);
    }

    @GetMapping(value = "/getDeckByTitle")
    public ResponseEntity<Deck> getDeckByTitle(@RequestParam String title) {
        Deck deck = this.deckService.getDeckByTitle(title);
        return deck != null
                ? ResponseEntity.ok(deck)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public String add(@RequestBody Deck deck) {
        return deckService.add(deck);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<Deck> update(@RequestBody Deck deck) {
        Deck resultDeck = deckService.updateDeck(deck);
        if (resultDeck != null) {
            return new ResponseEntity<>(resultDeck, HttpStatus.OK); //returns 200 if ok
        }
        else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //returns 404 default
        }
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam int deckId) {
        return deckService.deleteDeckById(deckId);
    }
}
