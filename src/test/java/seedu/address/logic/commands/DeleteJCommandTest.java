package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalJobTitles.JOB_TITLE_IN_DEFAULT_LIST;
import static seedu.address.testutil.TypicalJobTitles.JOB_TITLE_NOT_IN_DEFAULT_LIST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.JobTitle;

public class DeleteJCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());

    @Test
    public void execute_validJobTitleList_success() {
        JobTitle jobTitleToDelete = model.getFilteredJobTitleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteJCommand deleteJCommand = new DeleteJCommand(jobTitleToDelete);

        String expectedMessage = String.format(DeleteJCommand.MESSAGE_SUCCESS,
                jobTitleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalScheduleBoard());
        expectedModel.deleteJobTitle(jobTitleToDelete);

        assertCommandSuccess(deleteJCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_jobTitleNotInList_throwsCommandException() {
        JobTitle invalidJobTitle = JOB_TITLE_NOT_IN_DEFAULT_LIST;
        DeleteJCommand deleteJCommand = new DeleteJCommand(invalidJobTitle);

        String expectedMessage = String.format(DeleteJCommand.MESSAGE_JOB_ROLE_NOT_FOUND,
                invalidJobTitle);

        assertCommandFailure(deleteJCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteJCommand deleteFirstJCommand = new DeleteJCommand(JOB_TITLE_IN_DEFAULT_LIST);
        DeleteJCommand deleteSecondJCommand = new DeleteJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);

        // same object -> returns true
        assertTrue(deleteFirstJCommand.equals(deleteFirstJCommand));

        // same values -> returns true
        DeleteJCommand deleteFirstCommandCopy = new DeleteJCommand(JOB_TITLE_IN_DEFAULT_LIST);
        assertTrue(deleteFirstJCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstJCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstJCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstJCommand.equals(deleteSecondJCommand));
    }

    @Test
    public void toStringMethod() {
        JobTitle validJobTitle = JOB_TITLE_IN_DEFAULT_LIST;
        DeleteJCommand deleteJCommand = new DeleteJCommand(validJobTitle);
        String expected = DeleteJCommand.class.getCanonicalName() + "{targetJobTitle=" + validJobTitle + "}";
        assertEquals(expected, deleteJCommand.toString());
    }
}
