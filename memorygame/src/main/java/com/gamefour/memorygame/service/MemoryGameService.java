package com.gamefour.memorygame.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamefour.memorygame.model.Card;
import com.gamefour.memorygame.repository.CardRepository;

@Service
public class MemoryGameService {
  private final CardRepository cardRepository;

	    // Constructor injection (Spring Boot will auto-inject the repo)
	    public MemoryGameService(CardRepository cardRepository) {
	        this.cardRepository = cardRepository;
	    }

	    private final List<String> symbols = Arrays.asList("🍎", "🐱", "🏀", "🚗", "🎵", "🌟");

	    // Start a new game
	    public List<Card> startNewGame() {
	        List<Card> cards = new ArrayList<>();
	        for (String symbol : symbols) {
	            cards.add(new Card(symbol));
	            cards.add(new Card(symbol)); // duplicate for pair
	        }
	        Collections.shuffle(cards);
	        return cardRepository.saveAll(cards);
	    }

	    // Fetch all cards
	    public List<Card> getAllCards() {
	        return cardRepository.findAll();
	    }

	    // Check if two cards match
	    public boolean checkMatch(Long id1, Long id2) {
	        Optional<Card> card1 = cardRepository.findById(id1);
	        Optional<Card> card2 = cardRepository.findById(id2);

	        if (card1.isPresent() && card2.isPresent()) {
	            if (card1.get().getSymbol().equals(card2.get().getSymbol())) {
	                card1.get().setMatched(true);
	                card2.get().setMatched(true);
	                cardRepository.saveAll(Arrays.asList(card1.get(), card2.get()));
	                return true;
	            }
	        }
	        return false;
	    }


}
