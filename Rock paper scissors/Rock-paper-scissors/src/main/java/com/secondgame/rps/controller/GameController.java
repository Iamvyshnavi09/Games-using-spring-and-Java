package com.secondgame.rps.controller;

import com.secondgame.rps.entity.GameResult;

import com.secondgame.rps.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rps")
public class GameController {

    private final String[] choices = {"rock", "paper", "scissors"};
    private final Random random = new Random();
    private final List<GameResult> history = new ArrayList<>();
    private int attemptCounter = 0;
    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private GameResultRepository repository; // ✅ Inject repository

    @PostMapping("/play")
    public Object play(@RequestParam String name, @RequestParam String choice) {
        if (attemptCounter >= MAX_ATTEMPTS) {
            return "No more attempts left! Game Over!";
        }

        String computerChoice = choices[random.nextInt(choices.length)];
        String result;

        if (choice.equals(computerChoice)) {
            result = "Draw!";
        } else if (
                (choice.equals("rock") && computerChoice.equals("scissors")) ||
                (choice.equals("paper") && computerChoice.equals("rock")) ||
                (choice.equals("scissors") && computerChoice.equals("paper"))
        ) {
            result = "You win!";
        } else {
            result = "Computer wins!";
        }

        attemptCounter++;

        // In-memory history
        GameResult gameResult = new GameResult(name, choice, computerChoice, result, attemptCounter);
        history.add(gameResult);

        // ✅ Save to DB
        GameResult entity = new GameResult(name, choice, computerChoice, result, attemptCounter);
        repository.save(entity);

        return gameResult;
    }

    @GetMapping("/history")
    public List<GameResult> getHistory() {
        return history;
    }

    @PostMapping("/reset")
    public String resetGame() {
        history.clear();
        attemptCounter = 0;
        return "Game reset. Start again!";
    }
}
