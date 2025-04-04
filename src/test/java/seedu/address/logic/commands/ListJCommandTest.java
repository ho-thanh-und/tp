package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.JobRole;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListJCommand.
 */
public class ListJCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleBoard());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getScheduleBoard());
    }

    @Test
    public void execute_list_showsValidList() {
        String expectedMessage = String.format(ListJCommand.MESSAGE_SUCCESS,
                model.getFilteredJobRolesList().stream().map(j -> j.value).sorted().collect(Collectors.joining("\n")));

        assertCommandSuccess(new ListJCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_list_showsValidEmptyList() {
        List<JobRole> jobRoleList = new ArrayList<>(model.getFilteredJobRolesList());
        for (JobRole jobRole : jobRoleList) {
            model.deleteJobRoles(jobRole);
        }

        List<JobRole> expectedjobRoleList = new ArrayList<>(expectedModel.getFilteredJobRolesList());
        for (JobRole jobRole : expectedjobRoleList) {
            expectedModel.deleteJobRoles(jobRole);
        }

        String expectedMessage = String.format(ListJCommand.MESSAGE_EMPTY_SUCCESS);

        assertCommandSuccess(new ListJCommand(), model, expectedMessage, expectedModel);
    }
}
