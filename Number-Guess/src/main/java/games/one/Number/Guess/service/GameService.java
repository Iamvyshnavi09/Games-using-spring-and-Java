package games.one.Number.Guess.service;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.*;

@Service
public class GameService {
    private int targetNumber;
    private int attempts;
    private final int maxAttempts = 5;
    private boolean gameOver;
    private int upperLimit;
    private String currentPlayer;

    // Simple leaderboard (Player → Best Score)
    private final Map<String, Integer> leaderboard = new HashMap<>();

    // Start game with difficulty and player name
    public void startGame(String difficulty, String playerName) {
        Random random = new Random();

        switch (difficulty.toLowerCase()) {
            case "easy":
                upperLimit = 50;
                break;
            case "hard":
                upperLimit = 500;
                break;
            case "medium":
            default:
                upperLimit = 100;
        }

        this.targetNumber = random.nextInt(upperLimit) + 1;
        this.attempts = 0;
        this.gameOver = false;
        this.currentPlayer = playerName;
    }

    // Guess number
    public String guessNumber(int guess) {
        if (gameOver) {
            return "Game over! Please start a new game.";
        }

        attempts++;

        if (guess == targetNumber) {
            gameOver = true;
            leaderboard.put(currentPlayer,
                    Math.min(leaderboard.getOrDefault(currentPlayer, maxAttempts + 1), attempts));
            return "🎉 Correct, " + currentPlayer + "! You guessed it in " + attempts + " attempts.";
        } else if (attempts >= maxAttempts) {
            gameOver = true;
            return "❌ Game Over, " + currentPlayer + "! The number was " + targetNumber;
        } else if (guess < targetNumber) {
            return "⬇ Too low! Try again.";
        } else {
            return "⬆ Too high! Try again.";
        }
    }

    // Check status
    public String getStatus() {
        if (gameOver) {
            return "Game finished after " + attempts + " attempts.";
        }
        return "Attempts used: " + attempts + "/" + maxAttempts;
    }

    // Get leaderboard
    public Map<String, Integer> getLeaderboard() {
        return leaderboard;
    }
}



