package tanchishe;

import javax.swing.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃蛇游戏");
        SnakePanel panel = new SnakePanel();
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();  // 根据内容自动调整窗口大小
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // 居中显示
        frame.setVisible(true);
        // 启动游戏
        panel.startGame();
    }
}
