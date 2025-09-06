package com.gamethree.jokegenerator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gamethree.jokegenerator.dto.JokeRequest;
import com.gamethree.jokegenerator.entity.Joke;
import com.gamethree.jokegenerator.service.JokeService;

@RestController
@RequestMapping("/api/jokes")
@Tag(name = "Jokes", description = "Endpoints for fetching and managing jokes")
public class JokeController {

    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/random")
    @Operation(summary = "Get a random joke")
    public Joke getRandomJoke() {
        return jokeService.getRandomJoke();
    }

    @GetMapping
    @Operation(summary = "Get all jokes")
    public List<Joke> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @PostMapping
    @Operation(summary = "Add a new joke", description = "Provide a JSON body with the field 'text'")
    public ResponseEntity<Joke> addJoke(@RequestBody JokeRequest request) {
        if (request.getText() == null || request.getText().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jokeService.addJoke(request.getText()));
    }

}
