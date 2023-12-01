import java.awt.*;

public class Bird extends FlappyBird {

    static Rectangle bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);

    public static void jump() {

        if (gameOver) {
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            CreatingColumns.columns.clear();
            yMotion = 0;
            score = 0;

            CreatingColumns.creatingColumns(true);
            CreatingColumns.creatingColumns(true);
            CreatingColumns.creatingColumns(true);
            CreatingColumns.creatingColumns(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }

            yMotion -= 10;
        }
    }
}