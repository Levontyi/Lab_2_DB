package database;

import java.sql.*;

public class Database {

    private static Database instance;

    private static final DatabaseProperties properties = PropertiesFactory.getProperties();

    public Database() {
        init();
    }

    public synchronized static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void init() {
        createSchema();
        createTableBestScore();
    }

    private void createSchema() {
        String sql = """
                create schema if not exists gametable;
                """;
        execute(sql);
    }

    public void execute(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
             statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createTableBestScore() {
        String sql = """
        create table if not exists gametable.bestscore (
            id serial primary key,
            bestscores int
        );
        """;

        execute(sql);
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getLogin(),
                properties.getPassword()
        );
    }

    public static int getResult() {
        ScoreRecording scoreRecording = new ScoreRecording();
        scoreRecording.getMaxResult();
        String sql = """
                select MAX(bestscores) from gametable.bestscore;
                """;

        int bestScore = 0;
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(sql);
            if (set.next()) {
                bestScore = set.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bestScore;
    }

}

