package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CANDIDATES_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULES_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATES_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULES_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.SUFFIX_OVERWRITE_FILE;
import static seedu.address.logic.parser.CliSyntax.SUFFIX_SAVE_ALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.storage.ManualStorage.EMPTY_PATH;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SaveCommand;

public class SaveCommandParserTest {
    private SaveCommandParser parser;
    private String validCandidatesFilePath;
    private String validSchedulesFilePath;

    @BeforeEach
    public void setUp() {
        this.validCandidatesFilePath = VALID_CANDIDATES_FILE_PATH;
        this.validSchedulesFilePath = VALID_SCHEDULES_FILE_PATH;
        this.parser = new SaveCommandParser();
    }

    @Test
    public void parse_bothFilesNotSpecified_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE);

        // empty argument string
        assertParseFailure(this.parser, "", expectedMessage);

        // argument string contains only whitespaces
        assertParseFailure(this.parser, " ", expectedMessage);

        // unrecognised tokens found in argument
        assertParseFailure(this.parser, "yes", expectedMessage);

        // invalid boolean flag format
        assertParseFailure(this.parser, " " + SUFFIX_SAVE_ALL + ":yes", expectedMessage);
        assertParseFailure(this.parser, " " + SUFFIX_OVERWRITE_FILE + " yes", expectedMessage);
        assertParseFailure(this.parser, " " + SUFFIX_SAVE_ALL + "1", expectedMessage);

        // save all flag given
        assertParseFailure(this.parser, " " + SUFFIX_SAVE_ALL, expectedMessage);

        // overwrite file flag given
        assertParseFailure(this.parser, " " + SUFFIX_OVERWRITE_FILE, expectedMessage);

        // both optional flags given
        assertParseFailure(this.parser, " " + SUFFIX_SAVE_ALL + " " + SUFFIX_OVERWRITE_FILE, expectedMessage);
    }

    @Test
    public void parse_onlyCandidateFileSpecified_success() {
        String candidateFileArgument = PREFIX_CANDIDATES_FILE_PATH + this.validCandidatesFilePath;

        String userInput = " " + candidateFileArgument;

        // Only candidates file path specified
        SaveCommand expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidate file path specified with trailing whitespace(s)
        userInput = " " + candidateFileArgument + "  ";
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidate file path specified with leading whitespace(s)
        userInput = " " + PREFIX_CANDIDATES_FILE_PATH + "   " + this.validCandidatesFilePath + "  ";
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidates file path specified with save all flag
        userInput = " " + candidateFileArgument + " " + SUFFIX_SAVE_ALL;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, true, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidates file path specified with overwrite file flag
        userInput = " " + SUFFIX_OVERWRITE_FILE + " " + candidateFileArgument;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, false, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidates file path specified with both flags
        userInput = " " + SUFFIX_SAVE_ALL + " " + candidateFileArgument + " " + SUFFIX_OVERWRITE_FILE;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), EMPTY_PATH, true, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyCandidateFileSpecifiedWithOtherInvalidTokens_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE);
        String candidateFileArgument = PREFIX_CANDIDATES_FILE_PATH + this.validCandidatesFilePath;

        // Unrecognised preamble provided
        String userInput = "yes " + candidateFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);

        // invalid boolean flag format
        userInput = " " + candidateFileArgument + " " + SUFFIX_SAVE_ALL + ":yes";
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + SUFFIX_OVERWRITE_FILE + " true " + candidateFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + SUFFIX_SAVE_ALL + "1 " + candidateFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
    }

    @Test
    public void parse_onlyScheduleFileSpecified_success() {
        String scheduleFileArgument = PREFIX_SCHEDULES_FILE_PATH + this.validSchedulesFilePath;

        String userInput = " " + scheduleFileArgument;

        // Only schedules file path specified
        SaveCommand expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Schedule file path specified with trailing whitespace(s)
        userInput = " " + scheduleFileArgument + "  ";
        expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Schedule file path specified with leading whitespace(s)
        userInput = " " + PREFIX_SCHEDULES_FILE_PATH + "   " + this.validSchedulesFilePath + "  ";
        expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Schedules file path specified with save all flag
        userInput = " " + scheduleFileArgument + " " + SUFFIX_SAVE_ALL;
        expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), true, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Schedules file path specified with overwrite file flag
        userInput = " " + SUFFIX_OVERWRITE_FILE + " " + scheduleFileArgument;
        expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), false, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Schedules file path specified with both flags
        userInput = " " + SUFFIX_SAVE_ALL + " " + scheduleFileArgument + " " + SUFFIX_OVERWRITE_FILE;
        expectedCommand = new SaveCommand(
                EMPTY_PATH, Path.of(this.validSchedulesFilePath), true, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyScheduleFileSpecifiedWithOtherInvalidTokens_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE);
        String scheduleFileArgument = PREFIX_SCHEDULES_FILE_PATH + this.validSchedulesFilePath;

        // Unrecognised preamble provided
        String userInput = "yes " + scheduleFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);

        // invalid boolean flag format
        userInput = " " + scheduleFileArgument + " " + SUFFIX_SAVE_ALL + ":yes";
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + SUFFIX_OVERWRITE_FILE + " true " + scheduleFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + SUFFIX_SAVE_ALL + "1 " + scheduleFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
    }

    @Test
    public void parse_bothFilesSpecified_success() {
        String candidateFileArgument = PREFIX_CANDIDATES_FILE_PATH + this.validCandidatesFilePath;
        String scheduleFileArgument = PREFIX_SCHEDULES_FILE_PATH + this.validSchedulesFilePath;

        // Only both file paths specified
        String userInput = " " + candidateFileArgument + " " + scheduleFileArgument;
        SaveCommand expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Switch order of both files
        userInput = " " + scheduleFileArgument + " " + candidateFileArgument;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Both file paths specified with leading/trailing whitespace(s)
        userInput = " " + PREFIX_SCHEDULES_FILE_PATH + " " + this.validSchedulesFilePath
                + "  " + PREFIX_CANDIDATES_FILE_PATH + "   " + this.validCandidatesFilePath + "  ";
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                false, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Both file paths specified with save all flag
        userInput = " " + candidateFileArgument + " " + scheduleFileArgument + " " + SUFFIX_SAVE_ALL;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                true, false);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidates file path specified with overwrite file flag
        userInput = " " + SUFFIX_OVERWRITE_FILE + " " + scheduleFileArgument + " " + candidateFileArgument;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                false, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);

        // Candidates file path specified with both flags
        userInput = " " + SUFFIX_SAVE_ALL + " " + candidateFileArgument
                + " " + scheduleFileArgument + " " + SUFFIX_OVERWRITE_FILE;
        expectedCommand = new SaveCommand(
                Path.of(this.validCandidatesFilePath), Path.of(this.validSchedulesFilePath),
                true, true);
        assertParseSuccess(this.parser, userInput, expectedCommand);
    }

    @Test
    public void parse_bothFilesSpecifiedWithOtherInvalidTokens_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE);
        String candidateFileArgument = PREFIX_CANDIDATES_FILE_PATH + this.validCandidatesFilePath;
        String scheduleFileArgument = PREFIX_SCHEDULES_FILE_PATH + this.validSchedulesFilePath;

        // Unrecognised preamble provided
        String userInput = "yes " + scheduleFileArgument + " " + candidateFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);

        // invalid boolean flag format
        userInput = " " + candidateFileArgument + " " + scheduleFileArgument + " " + SUFFIX_SAVE_ALL + ":yes";
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + candidateFileArgument + " " + SUFFIX_OVERWRITE_FILE + " true " + scheduleFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
        userInput = " " + SUFFIX_SAVE_ALL + "1 " + scheduleFileArgument + " " + candidateFileArgument;
        assertParseFailure(this.parser, userInput, expectedMessage);
    }
}
