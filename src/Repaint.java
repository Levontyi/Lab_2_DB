import java.awt.*;

public class Repaint extends FlappyBird {

    public static void repaint(Graphics g) {

        Image background = Toolkit.getDefaultToolkit().getImage("C:\\FlappyBird\\src\\Background.png");
        g.drawImage(background, 0, 0,Color.BLUE, null);
        //g.setColor(Color.cyan);
        //g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(Bird.bird.x, Bird.bird.y, Bird.bird.width, Bird.bird.height);

        for (int i = 0; i < CreatingColumns.columns.size(); i++) {
            CreatingColumns.paintColumn(g, CreatingColumns.columns.get(i));
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 50));

        if (!started) {
            g.drawString("Press space to start!", 150, HEIGHT / 2 - 200);
        }

        if (gameOver) {
            g.drawString("Game Over!", 250, 200);
            g.drawString("Press space to restart", 130, 330);
        }

        if (!gameOver && started) {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }
}
