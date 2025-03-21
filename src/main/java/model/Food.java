package model;

import java.util.Random;

public class Food {
    private int x;
    private int y;
    private static final int GRID_SIZE = 10;

    public Food() { spawn(); }

    public void spawn() {
        Random random = new Random();
        x = random.nextInt(GRID_SIZE);
        y = random.nextInt(GRID_SIZE);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
