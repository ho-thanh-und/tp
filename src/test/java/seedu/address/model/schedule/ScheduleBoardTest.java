package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_1;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.testutil.ScheduleBuilder;

public class ScheduleBoardTest {

    private final ScheduleBoard scheduleBoard = new ScheduleBoard();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduleBoard.getScheduleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleBoard.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyScheduleBoard_replacesData() {
        ScheduleBoard newData = getTypicalScheduleBoard();
        scheduleBoard.resetData(newData);
        assertEquals(newData, scheduleBoard);
    }

    @Test
    public void resetData_withDuplicateSchedules_throwsDuplicateScheduleException() {
        // Two Schedules with the same identity fields
        Schedule editedSchedule = new ScheduleBuilder(SCHEDULE_1)
                .build();
        List<Schedule> newSchedules = Arrays.asList(SCHEDULE_1, editedSchedule);
        ScheduleBoardTest.ScheduleBoardStub newData = new ScheduleBoardTest.ScheduleBoardStub(newSchedules);

        assertThrows(DuplicateScheduleException.class, () -> scheduleBoard.resetData(newData));
    }

    @Test
    public void hasSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleBoard.hasSchedule(null));
    }

    @Test
    public void hasSchedule_scheduleNotInScheduleBoard_returnsFalse() {
        assertFalse(scheduleBoard.hasSchedule(SCHEDULE_1));
    }

    @Test
    public void hasSchedule_scheduleInScheduleBoard_returnsTrue() {
        scheduleBoard.addSchedule(SCHEDULE_1);
        assertTrue(scheduleBoard.hasSchedule(SCHEDULE_1));
    }

    @Test
    public void hasSchedule_scheduleWithSameIdentityFieldsInScheduleBoard_returnsTrue() {
        scheduleBoard.addSchedule(SCHEDULE_1);
        Schedule editedSchedule = new ScheduleBuilder(SCHEDULE_1)
                .build();
        assertTrue(scheduleBoard.hasSchedule(editedSchedule));
    }

    @Test
    public void getScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduleBoard.getScheduleList().remove(0));
    }

    @Test
    public void equals() {
        assertTrue(scheduleBoard.equals(scheduleBoard));
        assertFalse(scheduleBoard.equals(null));
    }

    @Test
    public void toStringMethod() {
        String expected = ScheduleBoard.class.getCanonicalName() + "{Schedules=" + scheduleBoard.getScheduleList()
                + "}";
        assertEquals(expected, scheduleBoard.toString());
    }

    /**
     * A stub ReadOnlyScheduleBoard whose Schedules list can violate interface constraints.
     */
    private static class ScheduleBoardStub implements ReadOnlyScheduleBoard {
        private final ObservableList<Schedule> schedules = FXCollections.observableArrayList();

        ScheduleBoardStub(Collection<Schedule> schedules) {
            this.schedules.setAll(schedules);
        }
        @Override
        public ObservableList<Schedule> getScheduleList() {
            return schedules;
        }
    }

}
