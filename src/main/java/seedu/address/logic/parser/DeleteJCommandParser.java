package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteJCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.JobTitle;

/**
 * Parses input arguments and creates a new DeleteJCommand object.
 * Expected format: deleteJ JOB_TITLE
 */
public class DeleteJCommandParser implements Parser<DeleteJCommand> {

    @Override
    public DeleteJCommand parse(String args) throws ParseException {
        JobTitle targetJobTitle = ParserUtil.parseJobTitle(args);
        return new DeleteJCommand(targetJobTitle);
    }
}
