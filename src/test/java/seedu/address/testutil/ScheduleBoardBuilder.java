package seedu.address.testutil;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleBoard;

/**
 * A utility class to help with building ScheduleBoard objects.
 */
public class ScheduleBoardBuilder {

    private ScheduleBoard scheduleBoard;

    public ScheduleBoardBuilder() {
        scheduleBoard = new ScheduleBoard();
    }

    public ScheduleBoardBuilder(ScheduleBoard scheduleBoard) {
        this.scheduleBoard = scheduleBoard;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ScheduleBoardBuilder withSchedule(Schedule schedule) {
        scheduleBoard.addSchedule(schedule);
        return this;
    }

    public ScheduleBoard build() {
        return scheduleBoard;
    }
}

