package com.gamefour.memorygame.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamefour.memorygame.model.Card;
import com.gamefour.memorygame.service.MemoryGameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/memory")
@Tag(name = "Memory Game", description = "Play the memory card matching game")
public class MemoryGameController {

    private final MemoryGameService memoryGameService;

    public MemoryGameController(MemoryGameService memoryGameService) {
        this.memoryGameService = memoryGameService;
    }

    @PostMapping("/start")
    @Operation(summary = "Start a new memory game")
    public List<Card> startGame() {
        return memoryGameService.startNewGame();
    }

    @GetMapping("/cards")
    @Operation(summary = "Get all cards in the current game")
    public List<Card> getCards() {
        return memoryGameService.getAllCards();
    }

    @PostMapping("/match/{id1}/{id2}")
    @Operation(summary = "Check if two cards match by ID")
    public ResponseEntity<String> checkMatch(@PathVariable Long id1, @PathVariable Long id2) {
        boolean match = memoryGameService.checkMatch(id1, id2);
        if (match) {
            return ResponseEntity.ok("✅ It's a match!");
        } else {
            return ResponseEntity.ok("❌ Not a match!");
        }
    }
}
