package seedu.address.model.schedule;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_1;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;
import seedu.address.testutil.ScheduleBuilder;

public class UniqueScheduleListTest {

    private final UniqueScheduleList uniqueScheduleList = new UniqueScheduleList();

    @Test
    public void contains_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.contains(null));
    }

    @Test
    public void contains_scheduleNotInList_returnsFalse() {
        assertFalse(uniqueScheduleList.contains(SCHEDULE_1));
    }

    @Test
    public void contains_scheduleInList_returnsTrue() {
        uniqueScheduleList.add(SCHEDULE_1);
        assertTrue(uniqueScheduleList.contains(SCHEDULE_1));
    }


    @Test
    public void add_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.add(null));
    }

    @Test
    public void add_duplicateSchedule_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_1);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.add(SCHEDULE_1));
    }

    @Test
    public void setSchedule_nullTargetSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(null, SCHEDULE_1));
    }

    @Test
    public void setSchedule_nullEditedSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(SCHEDULE_1, null));
    }

    @Test
    public void setSchedule_targetScheduleNotInList_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.setSchedule(SCHEDULE_1, SCHEDULE_1));
    }

    @Test
    public void setSchedule_editedScheduleIsSameSchedule_success() {
        uniqueScheduleList.add(SCHEDULE_1);
        uniqueScheduleList.setSchedule(SCHEDULE_1, SCHEDULE_1);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_1);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasSameIdentity_success() {
        uniqueScheduleList.add(SCHEDULE_1);
        Schedule editedSchedule = new ScheduleBuilder(SCHEDULE_1).withDate(VALID_DATE_2)
                .withStartTime(VALID_START_TIME_2).withEndTime(VALID_END_TIME_2)
                .build();
        uniqueScheduleList.setSchedule(SCHEDULE_1, editedSchedule);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(editedSchedule);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasDifferentIdentity_success() {
        uniqueScheduleList.add(SCHEDULE_1);
        uniqueScheduleList.setSchedule(SCHEDULE_1, SCHEDULE_2);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_2);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasNonUniqueIdentity_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_1);
        uniqueScheduleList.add(SCHEDULE_2);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.setSchedule(SCHEDULE_1, SCHEDULE_2));
    }

    @Test
    public void remove_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.remove(null));
    }

    @Test
    public void remove_scheduleDoesNotExist_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.remove(SCHEDULE_1));
    }

    @Test
    public void remove_existingSchedule_removesSchedule() {
        uniqueScheduleList.add(SCHEDULE_1);
        uniqueScheduleList.remove(SCHEDULE_1);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullUniqueScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((UniqueScheduleList) null));
    }

    @Test
    public void setSchedules_uniqueScheduleList_replacesOwnListWithProvidedUniqueScheduleList() {
        uniqueScheduleList.add(SCHEDULE_1);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_2);
        uniqueScheduleList.setSchedules(expectedUniqueScheduleList);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((List<Schedule>) null));
    }

    @Test
    public void setSchedules_list_replacesOwnListWithProvidedList() {
        uniqueScheduleList.add(SCHEDULE_1);
        List<Schedule> scheduleList = Collections.singletonList(SCHEDULE_2);
        uniqueScheduleList.setSchedules(scheduleList);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_2);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_listWithDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> listWithDuplicateSchedules = Arrays.asList(SCHEDULE_1, SCHEDULE_1);
        assertThrows(DuplicateScheduleException.class, () ->
                uniqueScheduleList.setSchedules(listWithDuplicateSchedules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueScheduleList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueScheduleList.asUnmodifiableObservableList().toString(), uniqueScheduleList.toString());
    }
}
