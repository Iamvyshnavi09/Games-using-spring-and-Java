package com.secondgame.rps.entity;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

@Entity
	public class GameResult {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String playerName;
	    private String playerChoice;
	    private String computerChoice;
	    private String result;
	    private int attempt;
	

	   

	        public GameResult(String playerName, String playerChoice, String computerChoice, String result, int attempt) {
	            this.playerName = playerName;
	            this.playerChoice = playerChoice;
	            this.computerChoice = computerChoice;
	            this.result = result;
	            this.attempt = attempt;
	        }

	        // Getters
	        public String getPlayerName() { return playerName; }
	        public String getPlayerChoice() { return playerChoice; }
	        public String getComputerChoice() { return computerChoice; }
	        public String getResult() { return result; }
	        public int getAttempt() { return attempt; }
	    }
