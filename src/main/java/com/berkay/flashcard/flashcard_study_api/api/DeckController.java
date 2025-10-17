package com.berkay.flashcard.flashcard_study_api.api;

import com.berkay.flashcard.flashcard_study_api.business.DeckService;
import com.berkay.flashcard.flashcard_study_api.entities.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/decks")
@RestController
@CrossOrigin
public class DeckController {

    private DeckService deckService;

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

    @PostMapping("/add")
    public String add(@RequestBody Deck deck) {
        return deckService.add(deck);
    }

    @GetMapping(value = "/getDeckByTitle")
    public Deck getDeckByTitle(@RequestParam String title) {
        return this.deckService.getDeckByTitle(title);
    }

}
