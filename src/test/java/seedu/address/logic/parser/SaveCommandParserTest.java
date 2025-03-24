package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_PATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SaveCommand;

public class SaveCommandParserTest {
    private SaveCommandParser parser;
    private String nonEmptyFilePath;

    @BeforeEach
    public void setUp() {
        this.nonEmptyFilePath = VALID_FILE_PATH;
        this.parser = new SaveCommandParser();
    }

    @Test
    public void parse_nonEmptyFilePathSpecified_success() {
        String userInput = this.nonEmptyFilePath;
        SaveCommand expectedCommand = new SaveCommand(Path.of(this.nonEmptyFilePath));
        assertParseSuccess(this.parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyFilePathSpecified_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE);

        // with trailing whitespace (if user mistyped)
        assertParseFailure(this.parser, " ", expectedMessage);

        // without trailing whitespace
        assertParseFailure(this.parser, "", expectedMessage);
    }
}
