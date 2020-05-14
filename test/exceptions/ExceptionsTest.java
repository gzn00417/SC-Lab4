package exceptions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import planningEntry.FlightSchedule;
import planningEntryCollection.*;
import resource.Resource;

import java.io.*;

public class ExceptionsTest {
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    public final FlightScheduleCollection flightScheduleCollection = new FlightScheduleCollection();

    public String getOneData(String fileName) throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader(new File(fileName)));
        String line = "";
        int cntLine = 0;
        StringBuilder stringInfo = new StringBuilder("");
        while ((line = bReader.readLine()) != null) {
            if (line.equals(""))
                continue;
            stringInfo.append(line + "\n");
            cntLine++;
            if (cntLine % 13 == 0)
                break;
        }
        bReader.close();
        return stringInfo.toString();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testDataPatternException() throws Exception {
        exception.expect(DataPatternException.class);
        String data = getOneData("data/Exceptions/DataPatternException.txt");
        exception.expectMessage("Data: " + data + " mismatch Pattern.");
        flightScheduleCollection.addPlanningEntry(data);
    }

    @Test
    public void testEntryNumberFormatException() throws Exception {
        exception.expect(EntryNumberFormatException.class);
        exception.expectMessage("AAAA00003" + " has incorrect format.");
        String data = getOneData("data/Exceptions/EntryNumberFormatException.txt");
        flightScheduleCollection.addPlanningEntry(data);
    }

    @Test
    public void testSameAirportException() throws Exception {
        exception.expect(SameAirportException.class);
        exception.expectMessage("Harbin" + " is the same with " + "Harbin" + " .");
        String data = getOneData("data/Exceptions/SameAirportException.txt");
        flightScheduleCollection.addPlanningEntry(data);
    }

    @Test
    public void testTimeOrderException() throws Exception {
        exception.expect(TimeOrderException.class);
        exception.expectMessage(
                "Departure time " + "2020-01-26 17:19" + " is not before arrival time " + "2020-01-25 18:47" + " .");
        String data = getOneData("data/Exceptions/TimeOrderException.txt");
        flightScheduleCollection.addPlanningEntry(data);
    }

    @Test
    public void testPlaneNumberFormatException() throws Exception {
        exception.expect(PlaneNumberFormatException.class);
        exception.expectMessage("B715+" + " has incorrect format.");
        String data = getOneData("data/Exceptions/PlaneNumberFormatException.txt");
        FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(data);
        flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), data);
    }

    @Test
    public void testPlaneTypeException() throws Exception {
        exception.expect(PlaneTypeException.class);
        exception.expectMessage("A+350" + " has incorrect format.");
        String data = getOneData("data/Exceptions/PlaneTypeException.txt");
        FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(data);
        flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), data);
    }

    @Test
    public void testPlaneSeatRangeException() throws Exception {
        exception.expect(PlaneSeatRangeException.class);
        exception.expectMessage(1000 + " is not in [50, 600].");
        String data = getOneData("data/Exceptions/PlaneSeatRangeException.txt");
        FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(data);
        flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), data);
    }

    @Test
    public void testPlaneAgeFormatException() throws Exception {
        exception.expect(PlaneAgeFormatException.class);
        exception.expectMessage(2.134 + " has incorrect format.");
        String data = getOneData("data/Exceptions/PlaneAgeFormatException.txt");
        FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(data);
        flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), data);
    }
}