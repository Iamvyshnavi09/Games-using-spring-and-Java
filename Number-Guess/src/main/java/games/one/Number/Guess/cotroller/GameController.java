package games.one.Number.Guess.cotroller;
import org.springframework.web.bind.annotation.*;

import games.one.Number.Guess.service.GameService;


import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Start game with difficulty + player name
    @PostMapping("/start/{difficulty}/{player}")
    public String startGame(@PathVariable String difficulty, @PathVariable String player) {
        gameService.startGame(difficulty, player);
        return "New " + difficulty + " game started for " + player + "! Guess the number.";
    }

    @PostMapping("/guess/{number}")
    public String makeGuess(@PathVariable int number) {
        return gameService.guessNumber(number);
    }

    @GetMapping("/status")
    public String checkStatus() {
        return gameService.getStatus();
    }

    @GetMapping("/leaderboard")
    public Map<String, Integer> getLeaderboard() {
        return gameService.getLeaderboard();
    }
}



