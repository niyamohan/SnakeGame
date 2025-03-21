package controller;

import model.Snake;
import model.Food;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game")
//允许React访问
@CrossOrigin(origins = "http://localhost:3000")  
public class GameController {
    private Snake snake = new Snake();
    private Food food = new Food();

    @PostMapping("/start")
    public Map<String, Object> startGame() {
        snake = new Snake();
        food.spawn();
        return getGameState();
    }

    @PostMapping("/move/{direction}")
    public Map<String, Object> move(@PathVariable String direction) {
        snake.move(direction);
        if (snake.eat(food)) {
            food.spawn();
        }
        return getGameState();
    }

    @GetMapping("/state")
    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("snake", snake.getBody());
        state.put("food", new int[]{food.getX(), food.getY()});
        return state;
    }
}
