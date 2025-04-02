package seedu.address.commons.util;

import java.time.LocalTime;

/**
 * ScheduleUtil contains methods for manipulating Schedules.
 */
public class ScheduleUtil {

    /**
     * Check that {@code startTime} provided is earlier than {@code endTime} provided
     * @return true if startTime is before endTime
     */
    public static boolean checkStartEndDateTime(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }
}
