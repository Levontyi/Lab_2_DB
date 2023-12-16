import database.Database;
import database.ScoreRecording;

import java.awt.*;

public class Repaint extends FlappyBird {

    public static void repaint(Graphics g) {

        Image background = Toolkit.getDefaultToolkit().getImage("C:\\FlappyBird\\src\\main\\resource\\Background.png");
        g.drawImage(background, 0, 0,Color.BLUE, null);

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
            g.drawString("Your score: " + FlappyBird.score, 230, 250);
            g.drawString("Best score: " + record(), 230, 300);
            g.drawString("Press space to restart", 130, 350);
        }

        if (!gameOver && started) {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }

    private static int record() {
        Database database = new Database();
        ScoreRecording scoreRecording = new ScoreRecording();

        scoreRecording.createNewRecord(score);
        bestScore = Database.getResult();

        return bestScore;
    }

}