package controller;

import model.Snake;
import service.UserService;
import model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import entity.ReturnObj;
import entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")  // 允许React访问
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class); // 日志记录器

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

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ReturnObj login(@RequestBody JSONObject params) {
        // 记录进入login方法的日志
        logger.info("Entering login method with params: {}", params);

        String username = params.getString("username");
        String password = params.getString("password");
        User user = new User(username, password);

        // 调用验证用户的服务
        Boolean result = userService.verifyUser(user);

        if (result) {
            logger.info("User {} logged in successfully", username);
            return ReturnObj.buildSuccess();
        } else {
            logger.warn("Failed login attempt for user {}", username);
            return ReturnObj.buildFault();
        }
    }
}
