package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalJobRoles.JOB_ROLES_NOT_IN_DEFAULT_LIST;
import static seedu.address.testutil.TypicalJobRoles.JOB_ROLE_IN_DEFAULT_LIST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.JobRole;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteJCommand}.
 */
public class DeleteJCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());

    @Test
    public void execute_validJobRoleList_success() {
        JobRole jobRoleToDelete = model.getFilteredJobRolesList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteJCommand deleteJCommand = new DeleteJCommand(jobRoleToDelete);

        String expectedMessage = String.format(DeleteJCommand.MESSAGE_SUCCESS,
                jobRoleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalScheduleBoard());
        expectedModel.deleteJobRoles(jobRoleToDelete);

        assertCommandSuccess(deleteJCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_jobRoleNotInList_throwsCommandException() {
        JobRole invalidJobRole = JOB_ROLES_NOT_IN_DEFAULT_LIST;
        DeleteJCommand deleteJCommand = new DeleteJCommand(invalidJobRole);

        String expectedMessage = String.format(DeleteJCommand.MESSAGE_JOB_ROLE_NOT_FOUND,
                invalidJobRole);

        assertCommandFailure(deleteJCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteJCommand deleteFirstJCommand = new DeleteJCommand(JOB_ROLE_IN_DEFAULT_LIST);
        DeleteJCommand deleteSecondJCommand = new DeleteJCommand(JOB_ROLES_NOT_IN_DEFAULT_LIST);

        // same object -> returns true
        assertTrue(deleteFirstJCommand.equals(deleteFirstJCommand));

        // same values -> returns true
        DeleteJCommand deleteFirstCommandCopy = new DeleteJCommand(JOB_ROLE_IN_DEFAULT_LIST);
        assertTrue(deleteFirstJCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstJCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstJCommand.equals(null));

        // different job role -> returns false
        assertFalse(deleteFirstJCommand.equals(deleteSecondJCommand));
    }

    @Test
    public void toStringMethod() {
        JobRole validJobRole = JOB_ROLE_IN_DEFAULT_LIST;
        DeleteJCommand deleteJCommand = new DeleteJCommand(validJobRole);
        String expected = DeleteJCommand.class.getCanonicalName() + "{targetJobRole=" + validJobRole + "}";
        assertEquals(expected, deleteJCommand.toString());
    }
}
