package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERRIDE_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SAVE_ALL;

import java.nio.file.Path;

import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser input arguments and creates a new {@code SaveCommand} object
 */
public class SaveCommandParser implements Parser<SaveCommand> {
    @Override
    public SaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_FILE, PREFIX_SAVE_ALL, PREFIX_OVERRIDE_FILE);

        if (argMultimap.getValue(PREFIX_FILE).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FILE, PREFIX_SAVE_ALL, PREFIX_OVERRIDE_FILE);

        Path path = ParserUtil.parsePath(argMultimap.getValue(PREFIX_FILE).get());
        boolean saveAll = argMultimap.getValue(PREFIX_SAVE_ALL).isPresent();
        boolean overrideFile = argMultimap.getValue(PREFIX_OVERRIDE_FILE).isPresent();

        return new SaveCommand(path, saveAll, overrideFile);
    }
}
