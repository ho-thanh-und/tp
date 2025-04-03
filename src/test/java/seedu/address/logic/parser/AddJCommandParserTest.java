package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddJCommand;
import seedu.address.model.person.JobTitle;

public class AddJCommandParserTest {
    private AddJCommandParser parser = new AddJCommandParser();

    @Test
    public void parse_validArgs_returnsAddJCommandParser() {
        String jobTitle = "Software Engineer";
        assertParseSuccess(parser, "Software Engineer", new AddJCommand(new JobTitle(jobTitle)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "S0ftwar3 3ng!n33r", String.format(JobTitle.MESSAGE_NEW_CONSTRAINTS));
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddJCommand.MESSAGE_USAGE));
    }
}
