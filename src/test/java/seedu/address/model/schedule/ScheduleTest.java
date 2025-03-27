package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_2;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_1;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {

    @Test
    public void isSameSchedule() {
        // same object -> returns true
        assertTrue(SCHEDULE_1.equals(SCHEDULE_1));

        // null -> returns false
        assertFalse(SCHEDULE_1.equals(null));

        // different name, all other attributes same -> returns false
        Schedule editedFirstSchedule = new ScheduleBuilder(SCHEDULE_1).withDate(VALID_DATE_2).build();
        assertFalse(SCHEDULE_1.equals(editedFirstSchedule));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Schedule scheduleCopy = new ScheduleBuilder(SCHEDULE_1).build();
        assertTrue(SCHEDULE_1.equals(scheduleCopy));

        // same object -> returns true
        assertTrue(SCHEDULE_1.equals(SCHEDULE_1));

        // null -> returns false
        assertFalse(SCHEDULE_1.equals(null));

        // different type -> returns false
        assertFalse(SCHEDULE_1.equals(5));

        // different Schedule -> returns false
        assertFalse(SCHEDULE_1.equals(SCHEDULE_2));

        // different date -> returns false
        Schedule editedSchedule = new ScheduleBuilder(SCHEDULE_1).withDate(VALID_DATE_2).build();
        assertFalse(SCHEDULE_1.equals(editedSchedule));

        // different star time -> returns false
        editedSchedule = new ScheduleBuilder(SCHEDULE_1).withStartTime(VALID_START_TIME_2).build();
        assertFalse(SCHEDULE_1.equals(editedSchedule));

        // different end time -> returns false
        editedSchedule = new ScheduleBuilder(SCHEDULE_1).withEndTime(VALID_END_TIME_2).build();
        assertFalse(SCHEDULE_1.equals(editedSchedule));

    }

    @Test
    public void toStringMethod() {
        String expected = Schedule.class.getCanonicalName() + "{date=" + SCHEDULE_1.getDate()
                + ", startTime=" + SCHEDULE_1.getStartTime()
                + ", endTime=" + SCHEDULE_1.getEndTime() + ", mode=" + SCHEDULE_1.getMode() + "}";

        assertEquals(expected, SCHEDULE_1.toString());
    }
}
