package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATES_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULES_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.SUFFIX_OVERWRITE_FILE;
import static seedu.address.logic.parser.CliSyntax.SUFFIX_SAVE_ALL;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.ManualStorage;

/**
 * Parser input arguments and creates a new {@code SaveCommand} object
 */
public class SaveCommandParser implements Parser<SaveCommand> {
    @Override
    public SaveCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CANDIDATES_FILE_PATH, PREFIX_SCHEDULES_FILE_PATH,
                SUFFIX_SAVE_ALL, SUFFIX_OVERWRITE_FILE);

        Path candidatesFilePath = getCandidatesFilePath(argMultimap);
        Path schedulesFilePath = getSchedulesFilePath(argMultimap);

        validateArgumentFormat(argMultimap, candidatesFilePath, schedulesFilePath);

        boolean shouldSaveAllData = validateBooleanFlag(argMultimap, SUFFIX_SAVE_ALL);
        boolean shouldOverrideFile = validateBooleanFlag(argMultimap, SUFFIX_OVERWRITE_FILE);

        return new SaveCommand(candidatesFilePath, schedulesFilePath, shouldSaveAllData, shouldOverrideFile);
    }

    private static Path getCandidatesFilePath(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> path = argMultimap.getValue(PREFIX_CANDIDATES_FILE_PATH);
        if (path.isEmpty()) {
            return ManualStorage.EMPTY_PATH;
        } else {
            return ParserUtil.parsePath(path.get());
        }
    }

    private static Path getSchedulesFilePath(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> path = argMultimap.getValue(PREFIX_SCHEDULES_FILE_PATH);
        if (path.isEmpty()) {
            return ManualStorage.EMPTY_PATH;
        } else {
            return ParserUtil.parsePath(path.get());
        }
    }

    private static void validateArgumentFormat(ArgumentMultimap argMultimap,
            Path candidatesFilePath, Path schedulesFilePath) throws ParseException {
        boolean hasCandidateFilePath = !candidatesFilePath.equals(ManualStorage.EMPTY_PATH);
        boolean hasSchedulesFilePath = !schedulesFilePath.equals(ManualStorage.EMPTY_PATH);
        boolean hasNoFilePaths = !hasCandidateFilePath && !hasSchedulesFilePath;
        boolean hasArgumentPreamble = !argMultimap.getPreamble().isEmpty();

        if (hasNoFilePaths || hasArgumentPreamble) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CANDIDATES_FILE_PATH, PREFIX_SCHEDULES_FILE_PATH,
                SUFFIX_SAVE_ALL, SUFFIX_OVERWRITE_FILE);
    }

    private static boolean validateBooleanFlag(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        Optional<String> booleanFlagValue = argMultimap.getValue(prefix);

        if (booleanFlagValue.isEmpty()) {
            return false;
        }

        if (!booleanFlagValue.get().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
        }

        return true;
    }
}
