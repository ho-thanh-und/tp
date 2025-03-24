package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_PATH;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class SaveCommandTest {
    public void execute() {
        // TODO: Add tests for SaveCommand#execute()
    }

    @Test
    public void equals() {
        Path validFilePath = Path.of(VALID_FILE_PATH);
        final SaveCommand standardCommand = new SaveCommand(validFilePath);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // same values -> equal
        SaveCommand commandWithSameValues = new SaveCommand(validFilePath);
        assertEquals(standardCommand, commandWithSameValues);

        // null -> no equal
        assertNotEquals(null, standardCommand);

        // different types -> not equal
        assertNotEquals(new ClearCommandTest(), standardCommand);

        // different path -> not equal
        assertNotEquals(new SaveCommand(Path.of("/" + VALID_FILE_PATH)), standardCommand);

        // same path but different format (i.e., relative and absolute) -> equal
        assertEquals(new SaveCommand(validFilePath.toAbsolutePath()), standardCommand);
    }
}
