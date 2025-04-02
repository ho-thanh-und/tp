package seedu.address.logic.commands.schedule;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ScheduleBoard;

public class ClearScheduleCommandTest {
    @Test
    public void execute_emptyScheduleBoard_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearScheduleCommand(), model, ClearScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyScheduleBoard_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
        expectedModel.setScheduleBoard(new ScheduleBoard());

        assertCommandSuccess(new ClearScheduleCommand(), model, ClearScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
