package model;

import java.util.LinkedList;

public class Snake {
    private LinkedList<int[]> body = new LinkedList<>();
    private String direction = "right";

    public Snake() {
        body.add(new int[]{5, 5});
    }

    public void move(String newDirection) {
        if (!oppositeDirection(newDirection)) {
            direction = newDirection;
        }
        int[] head = body.getFirst().clone();
        switch (direction) {
            case "up": head[1]--; break;
            case "down": head[1]++; break;
            case "left": head[0]--; break;
            case "right": head[0]++; break;
        }
        body.addFirst(head);
        body.removeLast();
    }

    public boolean eat(Food food) {
        if (body.getFirst()[0] == food.getX() && body.getFirst()[1] == food.getY()) {
            body.addLast(new int[]{-1, -1}); // 增长身体
            return true;
        }
        return false;
    }

    public LinkedList<int[]> getBody() { return body; }

    private boolean oppositeDirection(String newDir) {
        return (direction.equals("up") && newDir.equals("down")) ||
               (direction.equals("down") && newDir.equals("up")) ||
               (direction.equals("left") && newDir.equals("right")) ||
               (direction.equals("right") && newDir.equals("left"));
    }
}
