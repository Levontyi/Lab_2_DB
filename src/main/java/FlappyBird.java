import database.Database;
import database.ScoreRecording;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class FlappyBird implements ActionListener, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public Renderer renderer;
    public int ticks;
    public static int yMotion;
    public static int score;
    public static boolean started;
    public static boolean gameOver;
    public Random rand;
    public static int bestScore;

    public FlappyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new Renderer();
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        Bird.bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        CreatingColumns.columns = new ArrayList<Rectangle>();

        CreatingColumns.creatingColumns(true);
        CreatingColumns.creatingColumns(true);
        CreatingColumns.creatingColumns(true);
        CreatingColumns.creatingColumns(true);

        if (gameOver) {
            ScoreRecording scoreRecording = new ScoreRecording();
            scoreRecording.createNewRecord(score);
            bestScore = Database.getResult();
            CreatingColumns.creatingColumns(false);
        }

        timer.start();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            Bird.jump();
        }
    }

    public void actionPerformed(ActionEvent e) {
        int speed = 10;

        if (gameOver) {
            speed = 0;
        }
        ticks++;

        if (started) {
            for (int i = 0; i < CreatingColumns.columns.size(); i++)
            {
                Rectangle column = CreatingColumns.columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15)
            {
                yMotion += 2;
            }

            for (int i = 0; i < CreatingColumns.columns.size(); i++)
            {
                Rectangle column = CreatingColumns.columns.get(i);

                if (column.x + column.width < 0)
                {
                    CreatingColumns.columns.remove(column);

                    if (column.y == 0)
                    {
                        CreatingColumns.creatingColumns(false);
                    }
                }
            }

            Bird.bird.y += yMotion;

            for (int i = 0; i < CreatingColumns.columns.size(); i++) {
                if (CreatingColumns.columns.get(i).y == 0 && Bird.bird.x + Bird.bird.width / 2 > CreatingColumns.columns.get(i).x + CreatingColumns.columns.get(i).width / 2 - 10 && Bird.bird.x + Bird.bird.width / 2 < CreatingColumns.columns.get(i).x + CreatingColumns.columns.get(i).width / 2 + 10) {
                    score++;
                }

                if (CreatingColumns.columns.get(i).intersects(Bird.bird)) {
                    gameOver = true;

                    if (Bird.bird.x <= CreatingColumns.columns.get(i).x) {
                        Bird.bird.x = CreatingColumns.columns.get(i).x - Bird.bird.width;
                    }
                    else {
                        if (CreatingColumns.columns.get(i).y != 0) {
                            Bird.bird.y = CreatingColumns.columns.get(i).y - Bird.bird.height;
                        }
                        else if (Bird.bird.y < CreatingColumns.columns.get(i).height) {
                            Bird.bird.y = CreatingColumns.columns.get(i).height;
                        }
                    }
                }
            }

            if (Bird.bird.y > HEIGHT - 120 || Bird.bird.y < 0) {
                gameOver = true;
            }

            if (Bird.bird.y + yMotion >= HEIGHT - 120) {
                Bird.bird.y = HEIGHT - 120 - Bird.bird.height;
                gameOver = true;
            }
        }

        renderer.repaint();
    }

    public void getMaxResult() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}