package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Theme;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ScheduleBoard;
public class ThemeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ScheduleBoard());
    @Test
    public void executeThemeChangeTest() {
        Theme themeToChange = Theme.LIGHT;

        ThemeCommand themeCommand = new ThemeCommand(themeToChange);

        String expectedMessage = String.format(ThemeCommand.MESSAGE_THEME_CHANGE_SUCCESS,
                themeToChange);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false,
                true);
        expectedCommandResult.setTheme(themeToChange);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getScheduleBoard());
        expectedModel.setTheme(Theme.LIGHT);

        assertCommandSuccess(themeCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ThemeCommand themeFirstCommand = new ThemeCommand(Theme.DARK);
        ThemeCommand themeSecondCommand = new ThemeCommand(Theme.LIGHT);

        // same object -> returns true
        assertTrue(themeFirstCommand.equals(themeFirstCommand));

        // same values -> returns true
        ThemeCommand themeFirstCommandCopy = new ThemeCommand(Theme.DARK);
        assertTrue(themeFirstCommand.equals(themeFirstCommandCopy));

        // different types -> returns false
        assertFalse(themeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(themeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(themeFirstCommand.equals(themeSecondCommand));
    }
}
