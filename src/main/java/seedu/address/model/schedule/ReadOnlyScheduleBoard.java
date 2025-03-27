package seedu.address.model.schedule;

import java.util.List;

/**
 * Unmodifiable view of all Schedules.
 */
public interface ReadOnlyScheduleBoard {
    /**
     * Returns an unmodifiable view of the schedule list.
     * This list will not contain any duplicate schedules.
     */
    List<Schedule> getScheduleList();
}
