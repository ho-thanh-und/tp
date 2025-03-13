package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.person.Schedule;

public class ScheduleCommandParserTest {
    private final String nonEmptySchedule = "Some schedule.";
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have schedule
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_SCHEDULE + nonEmptySchedule;
        ScheduleCommand expectedCommand = new ScheduleCommand(INDEX_FIRST_PERSON, new Schedule(nonEmptySchedule));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no schedule
        userInput = targetIndex.getOneBased() + " " + PREFIX_SCHEDULE;
        expectedCommand = new ScheduleCommand(INDEX_FIRST_PERSON, new Schedule(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ScheduleCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, ScheduleCommand.COMMAND_WORD + " " + nonEmptySchedule, expectedMessage);
    }
}
