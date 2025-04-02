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
import seedu.address.model.person.JobTitle;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
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
                model.getFilteredJobTitleList().stream().map(j -> j.value).sorted().collect(Collectors.joining("\n")));

        assertCommandSuccess(new ListJCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_list_showsValidEmptyList() {
        List<JobTitle> jobTitleList = new ArrayList<>(model.getFilteredJobTitleList());
        for (JobTitle jobTitle : jobTitleList) {
            model.deleteJobTitle(jobTitle);
        }

        List<JobTitle> expectedjobTitleList = new ArrayList<>(expectedModel.getFilteredJobTitleList());
        for (JobTitle jobTitle : expectedjobTitleList) {
            expectedModel.deleteJobTitle(jobTitle);
        }

        String expectedMessage = String.format(ListJCommand.MESSAGE_EMPTY_SUCCESS);

        assertCommandSuccess(new ListJCommand(), model, expectedMessage, expectedModel);
    }
}
