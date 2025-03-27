package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleBoard;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
public class TypicalSchedules {

    public static final Schedule SCHEDULE_1 = new ScheduleBuilder().withDate("2025-03-15")
            .withStartTime("15:00").withEndTime("16:00").withMode("ONLINE")
            .withCandidateName("Amy").withCandidateEmail("amy@gmail.com")
            .build();
    public static final Schedule SCHEDULE_2 = new ScheduleBuilder().withDate("2025-04-15")
            .withStartTime("15:00").withEndTime("16:00").withMode("ONLINE")
            .withCandidateName("Bob").withCandidateEmail("bob@gmail.com")
            .build();
    public static final Schedule SCHEDULE_3 = new ScheduleBuilder().withDate("2025-03-20")
            .withStartTime("15:00").withEndTime("16:00").withMode("ONLINE")
            .withCandidateName("ben").withCandidateEmail("ben@gmail.com")
            .build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSchedules() {
    } // prevents instantiation

    /**
     * Returns an {@code ScheduleBoard} with all the typical schedules.
     */
    public static ScheduleBoard getTypicalScheduleBoard() {
        ScheduleBoard ab = new ScheduleBoard();
        for (Schedule schedule : getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        return ab;
    }

    public static List<Schedule> getTypicalSchedules() {
        return new ArrayList<>(Arrays.asList(SCHEDULE_1, SCHEDULE_2, SCHEDULE_3));
    }
}

