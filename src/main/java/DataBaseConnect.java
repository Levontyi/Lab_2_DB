import java.sql.*;

public class DataBaseConnect {


    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "76odudad";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/FlappyBird_DB";

    public static void insertingInTable() {
        String score = String.valueOf(FlappyBird.bestScore);

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bestscore");
            resultSet.last();
            int lastIndex = resultSet.getInt(1) + 1;

            ResultSet table = statement.executeQuery("INSERT * INTO bestscore(id, score) VALUES (" + lastIndex + ", " + FlappyBird.bestScore + ");");

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
