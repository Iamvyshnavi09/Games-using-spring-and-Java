package com.secondgame.rps.service;


import java.util.Random;

import org.springframework.stereotype.Service;

import com.secondgame.rps.entity.GameResult;
import com.secondgame.rps.repository.GameResultRepository;

@Service
public class GameService {

    private final String[] choices = {"rock", "paper", "scissors"};
    private final Random random = new Random();
    private final GameResultRepository repository;

    public GameService(GameResultRepository repository) {
        this.repository = repository;
    }

    public String play(String playerName, String playerChoice) {
        playerChoice = playerChoice.toLowerCase();
        String computerChoice = choices[random.nextInt(choices.length)];

        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "It's a Draw!";
        } else if (
                (playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (playerChoice.equals("scissors") && computerChoice.equals("paper")) ||
                (playerChoice.equals("paper") && computerChoice.equals("rock"))
        ) {
            result = "🎉 You Win!";
        } else {
            result = "💻 Computer Wins!";
        }

        // Save to DB
        GameResult gameResult = new GameResult(playerName, playerChoice, computerChoice, result, 0);
        repository.save(gameResult);

        return playerName + " chose: " + playerChoice +
                " | Computer chose: " + computerChoice +
                " → " + result;
    }
}


