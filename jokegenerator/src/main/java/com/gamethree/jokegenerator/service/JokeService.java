package com.gamethree.jokegenerator.service;

import java.util.NoSuchElementException;
import java.util.Random;
import com.gamethree.jokegenerator.entity.*;
import com.gamethree.jokegenerator.repository.JokeRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JokeService {

	
	    private final JokeRepository repository;
	    private final Random random = new Random();

	    public JokeService(JokeRepository repository) {
	        this.repository = repository;
	        if (repository.count() == 0) {
	            repository.saveAll(List.of(
	                new Joke("Why don’t skeletons fight each other? They don’t have the guts."),
	                new Joke("What do you call fake spaghetti? An impasta."),
	                new Joke("Why did the math book look sad? Because it had too many problems."),
	                new Joke("Parallel lines have so much in common. It’s a shame they’ll never meet.")
	            ));
	        }
	    }

	    public Joke getRandomJoke() {
	        List<Joke> all = repository.findAll();
	        if (all.isEmpty()) throw new NoSuchElementException("No jokes available!");
	        return all.get(random.nextInt(all.size()));
	    }

	    public List<Joke> getAllJokes() {
	        return repository.findAll();
	    }

	    public Joke addJoke(String text) {
	        return repository.save(new Joke(text));
	    }
	
}
