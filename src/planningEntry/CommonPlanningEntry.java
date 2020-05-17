package planningEntry;

import java.time.LocalDate;

import entryState.*;
import location.*;
import timeSlot.*;

/**
 * common planning entry implements similar methods in 3 types of planning entry
 */
public abstract class CommonPlanningEntry<R> implements PlanningEntry<R> {
    protected Location location;
    protected TimeSlot timeSlot;
    protected EntryState state;
    protected String strPlanningEntryType;
    protected String planningEntryNumber;
    protected R resource;

    /**
     * constructor
     * @param location
     * @param timeSlot
     * @param planningEntryNumber
     */
    public CommonPlanningEntry(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        this.location = location;
        this.timeSlot = timeSlot;
        this.planningEntryNumber = planningEntryNumber;
        state = new EntryState("Waiting");
    }

    @Override
    public Boolean start() {
        boolean flag = this.state.setNewState(strPlanningEntryType, "Running");
        assert (this.state != null && this.state.getState() != null);
        return flag;
    }

    @Override
    public Boolean block() {
        boolean flag = this.state.setNewState(strPlanningEntryType, "Blocked");
        assert (this.state != null && this.state.getState() != null);
        return flag;
    }

    @Override
    public Boolean cancel() {
        boolean flag = this.state.setNewState(strPlanningEntryType, "Cancelled");
        assert (this.state != null && this.state.getState() != null);
        return flag;
    }

    @Override
    public Boolean finish() {
        boolean flag = this.state.setNewState(strPlanningEntryType, "Ended");
        assert (this.state != null && this.state.getState() != null);
        return flag;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    @Override
    public EntryState getState() {
        return this.state;
    }

    @Override
    public String getStrPlanningEntryType() {
        return this.strPlanningEntryType;
    }

    @Override
    public String getPlanningEntryNumber() {
        return this.planningEntryNumber;
    }

    @Override
    public R getResource() {
        return this.resource;
    }

    /**
     * get the planning date
     * @return LocalDateTime of planning date
     */
    public abstract LocalDate getPlanningDate();

}
