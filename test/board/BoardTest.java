package board;

import java.io.IOException;

import org.junit.Test;

import planningEntryCollection.*;

public class BoardTest {
    @Test
    public void testShowLog() throws IOException {
        FlightBoard board = new FlightBoard(new FlightScheduleCollection());
        board.showLog(null, "", "");
    }
}