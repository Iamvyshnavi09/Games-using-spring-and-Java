package com.gamethree.jokegenerator.dto;

public class JokeRequest {
	
	    private String text;

	    public JokeRequest() {}

	    public JokeRequest(String text) {
	        this.text = text;
	    }

	    public String getText() { return text; }
	    public void setText(String text) { this.text = text; }
	}


