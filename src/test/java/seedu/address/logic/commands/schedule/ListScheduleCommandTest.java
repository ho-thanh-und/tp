package seedu.address.logic.commands.schedule;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListScheduleCommand.
 */
public class ListScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListScheduleCommand(), model, ListScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
