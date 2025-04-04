package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteScheduleCommand}.
 */
public class DeleteScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Schedule scheduleToDelete = model.getFilteredScheduleList().get(INDEX_FIRST.getZeroBased());
        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS,
                Messages.format(scheduleToDelete));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalScheduleBoard());
        expectedModel.deleteSchedule(scheduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Schedule scheduleToDelete = model.getFilteredScheduleList().get(INDEX_FIRST.getZeroBased());
        DeleteScheduleCommand deleteCommand = new DeleteScheduleCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS,
                Messages.format(scheduleToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
        expectedModel.deleteSchedule(scheduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteScheduleCommand deleteFirstScheduleCommand = new DeleteScheduleCommand(INDEX_FIRST);
        DeleteScheduleCommand deleteSecondScheduleCommand = new DeleteScheduleCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstScheduleCommand.equals(deleteFirstScheduleCommand));

        // same values -> returns true
        DeleteScheduleCommand deleteFirstScheduleCommandCopy = new DeleteScheduleCommand(INDEX_FIRST);
        assertTrue(deleteFirstScheduleCommand.equals(deleteFirstScheduleCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstScheduleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstScheduleCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstScheduleCommand.equals(deleteSecondScheduleCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(targetIndex);
        String expected = DeleteScheduleCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteScheduleCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSchedule(Model model) {
        model.updateFilteredScheduleList(p -> false);

        assertTrue(model.getFilteredScheduleList().isEmpty());
    }
}

