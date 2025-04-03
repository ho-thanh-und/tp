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
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalSchedules;

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

    @Test
    public void hasSameTime_emptyList_returnsFalse() {
        // When the list is empty, no schedule can clash.
        Schedule schedule = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("10:00")
                .withEndTime("11:00")
                .build();
        assertFalse(uniqueScheduleList.hasSameTime(schedule));
    }

    @Test
    public void hasSameTime_singleNonClashingSchedule_returnsFalse() {
        // Add a schedule that does not clash with the new schedule.
        Schedule scheduleInList = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("12:00")
                .withEndTime("13:00")
                .build();
        uniqueScheduleList.add(scheduleInList);

        Schedule newSchedule = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("10:00")
                .withEndTime("11:00")
                .build();
        // Expected: no time clash between scheduleInList and newSchedule.
        assertFalse(uniqueScheduleList.hasSameTime(newSchedule));
    }

    @Test
    public void hasSameTime_singleClashingSchedule_returnsTrue() {
        // Add a schedule.
        Schedule scheduleInList = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("10:00")
                .withEndTime("11:00")
                .build();
        uniqueScheduleList.add(scheduleInList);

        // Create a new schedule with identical timing.
        Schedule newSchedule = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("10:00")
                .withEndTime("11:00")
                .withCandidateName("Different Candidate")
                .build();
        // Since the list has one schedule and the new schedule clashes (assuming timeClash is defined naturally),
        // hasSameTime() should detect the clash.
        assertTrue(uniqueScheduleList.hasSameTime(newSchedule));
    }

    @Test
    public void hasSameTimeEdit_singleSchedule_returnsFalse() {
        // For edit, when there is only one schedule in the list (the schedule itself),
        // the method should return false because the clash count is 1 (not > 1).
        Schedule schedule = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("10:00")
                .withEndTime("11:00")
                .build();
        uniqueScheduleList.add(schedule);
        assertFalse(uniqueScheduleList.hasSameTimeEdit(schedule, schedule));
    }

    @Test
    public void getSchedule_validIndex_returnsSchedule() {
        uniqueScheduleList.add(TypicalSchedules.SCHEDULE_1);
        assertEquals(TypicalSchedules.SCHEDULE_1, uniqueScheduleList.getSchedule(0));
    }

    @Test
    public void getSchedule_invalidIndex_throwsIndexOutOfBoundsException() {
        uniqueScheduleList.add(TypicalSchedules.SCHEDULE_1);
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueScheduleList.getSchedule(1));
    }


    @Test
    public void editCandidateInSchedule_nullSchedule_throwsNullPointerException() {
        // Create a dummy person for candidate update.
        PersonBuilder personBuilder = new PersonBuilder();
        assertThrows(NullPointerException.class, () ->
                uniqueScheduleList.editCandidateInSchedule(null,
                        personBuilder.withName("New Name").withEmail("new@example.com").build()));
    }

    @Test
    public void editCandidateInSchedule_nullCandidate_throwsNullPointerException() {
        Schedule schedule = new ScheduleBuilder().build();
        uniqueScheduleList.add(schedule);
        assertThrows(NullPointerException.class, () ->
                uniqueScheduleList.editCandidateInSchedule(schedule, null));
    }

    @Test
    public void editCandidateInSchedule_scheduleNotInList_throwsScheduleNotFoundException() {
        Schedule schedule = new ScheduleBuilder().build();
        PersonBuilder personBuilder = new PersonBuilder();
        assertThrows(ScheduleNotFoundException.class, () ->
                uniqueScheduleList.editCandidateInSchedule(schedule,
                        personBuilder.withName("New Name").withEmail("new@example.com").build()));
    }

    @Test
    public void editCandidateInSchedule_validEdit_updatesCandidateInfo() {
        Schedule schedule = new ScheduleBuilder()
                .withCandidateName("Old Name")
                .withCandidateEmail("old@example.com")
                .build();
        uniqueScheduleList.add(schedule);

        PersonBuilder personBuilder = new PersonBuilder();
        var newCandidate = personBuilder.withName("New Name").withEmail("new@example.com").build();

        uniqueScheduleList.editCandidateInSchedule(schedule, newCandidate);
        assertEquals("New Name", uniqueScheduleList.getSchedule(0).getCandidateName().toString());
        assertEquals("new@example.com", uniqueScheduleList.getSchedule(0).getCandidateEmail().toString());
    }

    @Test
    public void getAllSchedules_returnsModifiableList() {
        uniqueScheduleList.add(TypicalSchedules.SCHEDULE_1);
        ObservableList<Schedule> allSchedules = uniqueScheduleList.getAllSchedules();
        allSchedules.add(TypicalSchedules.SCHEDULE_2);

        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(TypicalSchedules.SCHEDULE_1);
        expectedUniqueScheduleList.add(TypicalSchedules.SCHEDULE_2);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void iterator_returnsAllSchedulesInSortedOrder() {
        Schedule schedule1 = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("09:00")
                .withEndTime("10:00")
                .build();
        Schedule schedule2 = new ScheduleBuilder()
                .withDate("2025-03-27")
                .withStartTime("11:00")
                .withEndTime("12:00")
                .build();
        uniqueScheduleList.add(schedule2);
        uniqueScheduleList.add(schedule1);

        Iterator<Schedule> iterator = uniqueScheduleList.iterator();
        assertEquals(schedule1, iterator.next());
        assertEquals(schedule2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
