package tanchishe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private static final int GRID_SIZE = 20;  // 每个格子的大小
    private static final int WIDTH = 30;      // 游戏区域的宽度（格子数）
    private static final int HEIGHT = 30;     // 游戏区域的高度（格子数）
    
    private LinkedList<Point> snake;         // 蛇的身体
    private Point food;                      // 食物位置
    private boolean gameOver;                // 游戏是否结束
    private int direction;                   // 当前蛇的运动方向
    private Timer timer;                     // 定时器控制游戏速度
    private Random random;                   // 用于生成食物位置
    private JButton restartButton;           // 重新开始按钮

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    public SnakePanel() {
        setFocusable(true);
        addKeyListener(this);
        snake = new LinkedList<>();
        random = new Random();
        direction = RIGHT;
        gameOver = false;
        timer = new Timer(100, this);
        
        // 设置面板大小
        this.setPreferredSize(new Dimension(WIDTH * GRID_SIZE, HEIGHT * GRID_SIZE));
        
        // 创建“再次挑战”按钮
        restartButton = new JButton("再次挑战");
        restartButton.setFont(new Font("微软雅黑", Font.PLAIN, 20)); // 确保字体支持中文
        restartButton.setBounds(200, 250, 200, 50);
        restartButton.setVisible(false); // 初始时不可见
        restartButton.addActionListener(e -> startGame()); // 绑定事件
        this.setLayout(null);
        this.add(restartButton);
    }

    public void startGame() {
        snake.clear();
        snake.add(new Point(5, 5));
        spawnFood();
        gameOver = false;
        restartButton.setVisible(false);  // 隐藏按钮
        direction = RIGHT;
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // 清除画布
        drawGame(g);
    }

    public void drawGame(Graphics g) {
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 50));
            g.drawString("游戏结束", 150, 200);  // 位置调整

            // 显示“再次挑战”按钮
            restartButton.setVisible(true);
        } else {
            // 绘制蛇
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * GRID_SIZE, p.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }

            // 绘制食物
            g.setColor(Color.RED);
            g.fillRect(food.x * GRID_SIZE, food.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moveSnake();
            checkCollisions();
            repaint();
        }
    }

    public void moveSnake() {
        Point head = snake.getFirst();
        Point newHead = null;

        switch (direction) {
            case UP: newHead = new Point(head.x, head.y - 1); break;
            case RIGHT: newHead = new Point(head.x + 1, head.y); break;
            case DOWN: newHead = new Point(head.x, head.y + 1); break;
            case LEFT: newHead = new Point(head.x - 1, head.y); break;
        }

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            spawnFood();
        } else {
            snake.removeLast();
        }
    }

    public void checkCollisions() {
        Point head = snake.getFirst();

        // 碰墙检测
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver = true;
        }

        // 碰自己检测
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    public void spawnFood() {
        // 生成食物，确保不在蛇的身体上
        do {
            food = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        } while (snake.contains(food));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP && direction != DOWN) {
            direction = UP;
        } else if (keyCode == KeyEvent.VK_RIGHT && direction != LEFT) {
            direction = RIGHT;
        } else if (keyCode == KeyEvent.VK_DOWN && direction != UP) {
            direction = DOWN;
        } else if (keyCode == KeyEvent.VK_LEFT && direction != RIGHT) {
            direction = LEFT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
