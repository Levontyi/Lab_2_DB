import database.Database;
import database.ScoreRecording;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CreatingColumns extends FlappyBird {

    public static ArrayList<Rectangle> columns;

    public static void creatingColumns(boolean start) {

        int space = 300;
        int width = 100;
        Random rand = new Random();
        int height = 50 + rand.nextInt(300) + 150;

        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public static void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
}