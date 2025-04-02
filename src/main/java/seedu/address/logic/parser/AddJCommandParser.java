package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddJCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.JobTitle;

/**
 * Parses input arguments and creates a new AddJCommand object.
 * Expected format: addJ JOB_TITLE
 */
public class AddJCommandParser implements Parser<AddJCommand> {

    @Override
    public AddJCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJCommand.MESSAGE_USAGE));
        }
        JobTitle newJobTitle = ParserUtil.parseJobTitle(args);
        return new AddJCommand(newJobTitle);
    }
}
