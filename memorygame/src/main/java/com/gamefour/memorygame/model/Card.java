package com.gamefour.memorygame.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

	@Entity
	@Table(name = "cards")
	public class Card {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String symbol;
	    private boolean matched = false;

	    public Card() {}

	    public Card(String symbol) {
	        this.symbol = symbol;
	    }

	    // Getters & Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getSymbol() { return symbol; }
	    public void setSymbol(String symbol) { this.symbol = symbol; }

	    public boolean isMatched() { return matched; }
	    public void setMatched(boolean matched) { this.matched = matched; }
	}

    

