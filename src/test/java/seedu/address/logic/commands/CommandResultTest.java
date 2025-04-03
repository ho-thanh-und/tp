package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false,
                false, false, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true,
                false, false, null)));

        // different index value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                true, false, null)));

        CommandResult commandResultWithNewPerson = new CommandResult("feedback", true,
                false, false, false, null);

        // different changeTheme value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, true, null)));

        assertEquals(commandResult.getCandidateToShow(),
                new CommandResult("feedback", false, false, false, null).getCandidateToShow());


        commandResultWithNewPerson.setCandidateToShow(ALICE);
        assertNotEquals(commandResult.getCandidateToShow(), commandResultWithNewPerson.getCandidateToShow());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, false, null).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, false, null).hashCode());

        // different index value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, false, null).hashCode());

        // different changeTheme value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, true, null).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showCandidateFullDetails=" + commandResult.shouldShowNewCandidateFullDetails()
                + ", exit=" + commandResult.isExit()
                + ", hasThemeChanged=" + commandResult.shouldChangeTheme() + "}";

        assertEquals(expected, commandResult.toString());
    }
}
