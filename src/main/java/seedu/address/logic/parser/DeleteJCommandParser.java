package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteJCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.JobRole;

/**
 * Parses input arguments and creates a new DeleteJCommand object.
 * Expected format: deleteJ JOB_TITLE
 */
public class DeleteJCommandParser implements Parser<DeleteJCommand> {

    @Override
    public DeleteJCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteJCommand.MESSAGE_USAGE));
        }
        JobRole targetJobRole = ParserUtil.parseJobRole(args);
        return new DeleteJCommand(targetJobRole);
    }
}
