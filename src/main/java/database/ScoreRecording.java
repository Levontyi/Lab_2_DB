package database;

public class ScoreRecording {

    private final Database db = Database.getInstance();

    public void createNewRecord(int record) {
        String sql = """
                insert into gametable.bestscore
                (bestscores)
                values
                (%d)
                """;
        db.execute(String.format(sql, record));
    }

    public void getMaxResult() {
        String sql = """
                select MAX(bestscores) from gametable.bestscore
                """;
        db.execute(sql);
    }
}
