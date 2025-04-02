package seedu.address.logic.commands.schedule;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditScheduleCommand.
 */
public class EditScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());

    @Test
    public void execute_allFieldsSpecifiedUnfiScheduleList_success() {
        Schedule editedSchedule = new ScheduleBuilder().build();
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(editedSchedule).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(editScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getScheduleBoard());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSchedule = Index.fromOneBased(model.getFilteredScheduleList().size());
        Schedule lastSchedule = model.getFilteredScheduleList().get(indexLastSchedule.getZeroBased());

        ScheduleBuilder scheduleInList = new ScheduleBuilder(lastSchedule);
        Schedule editedSchedule = scheduleInList.withDate(VALID_DATE_2).withStartTime(VALID_START_TIME_2)
                .withEndTime(VALID_END_TIME_2).withMode(VALID_MODE_2).build();

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
                .withDate(VALID_DATE_2).withStartTime(VALID_START_TIME_2)
                .withEndTime(VALID_END_TIME_2).withMode(VALID_MODE_2).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(indexLastSchedule, descriptor);

        String expectedMessage = String.format(editScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getScheduleBoard());
        expectedModel.setSchedule(lastSchedule, editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST, new EditScheduleDescriptor());
        Schedule editedSchedule = model.getFilteredScheduleList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(editScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getScheduleBoard());

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withDate(VALID_DATE_2).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditScheduleCommand standardCommand = new EditScheduleCommand(INDEX_FIRST, DESC_SCHEDULE_1);

        // same values -> returns true
        EditScheduleDescriptor copyDescriptor = new EditScheduleDescriptor(DESC_SCHEDULE_1);
        EditScheduleCommand commandWithSameValues = new EditScheduleCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_SECOND, DESC_SCHEDULE_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_FIRST, DESC_SCHEDULE_2)));
    }

    @Test
    public void execute_duplicateScheduleFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Schedule scheduleInList = model.getScheduleBoard().getScheduleList().get(INDEX_SECOND.getZeroBased());
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST,
                new EditScheduleDescriptorBuilder(scheduleInList).build());

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_SCHEDULE_TIMING_CLASH);
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(index, editScheduleDescriptor);
        String expected = EditScheduleCommand.class.getCanonicalName() + "{index=" + index + ", editScheduleDescriptor="
                + editScheduleDescriptor + "}";
        assertEquals(expected, editScheduleCommand.toString());
    }
}
