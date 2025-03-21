package service;

import model.Snake;
import model.Food;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SnakeGameService {
    private Snake snake;
    private Food food;
    private boolean isGameOver;

    public Map<String, Object> startGame() {
        this.snake = new Snake();
        this.food = new Food();
        this.isGameOver = false;
        return getGameState();
    }

    public Map<String, Object> move(String direction) {
        Map<String, Object> response = new HashMap<>();
        
        if (isGameOver) {
            response.put("message", "Game over! Restart using /game/start");
            return response;
        }

        snake.move(direction);
        
        if (snake.eat(food)) {
            food.spawn(); // 重新生成食物
            response.put("message", "Snake ate food! New food at (" + food.getX() + ", " + food.getY() + ").");
        } else {
            response.put("message", "Snake moved " + direction + ".");
        }

        response.putAll(getGameState());
        return response;
    }

    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("snake", snake.getBody());
        state.put("food", new int[]{food.getX(), food.getY()});
        return state;
    }
}
