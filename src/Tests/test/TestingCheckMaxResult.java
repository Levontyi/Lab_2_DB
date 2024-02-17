import database.Database;
import database.ScoreRecording;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class TestingCheckMaxResult {
    @Test
    @DisplayName("Add new record in DB")

    public void testingMaxResult() {

        ScoreRecording scoreRecording = new ScoreRecording();
        scoreRecording.createNewRecord(1);
        scoreRecording.createNewRecord(2);

        int score = Database.getResult();
        then(score == 2);
    }
}
