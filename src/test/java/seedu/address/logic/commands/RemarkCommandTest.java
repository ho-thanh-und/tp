package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_FIRST_PERSON;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_SECOND_PERSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_LEETCODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_PASTTIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemarkCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Remark;

public class RemarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_LEETCODE));

        // same values -> equal
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_LEETCODE));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // null -> not equal
        assertNotEquals(null, standardCommand);

        // different types -> not equal
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> not equal
        assertNotEquals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(VALID_REMARK_LEETCODE)), standardCommand);

        // different remark -> not equal
        assertNotEquals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_PASTTIME)), standardCommand);
    }
}
