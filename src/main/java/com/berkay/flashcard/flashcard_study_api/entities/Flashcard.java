package com.berkay.flashcard.flashcard_study_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "flashcard")
public class Flashcard {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int flashcardId;

    private String question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public Flashcard() {
    }

    public Flashcard(int flashcardId, String question, String answer, Deck deck) {
        this.flashcardId = flashcardId;
        this.question = question;
        this.answer = answer;
        this.deck = deck;
    }

    public int getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
