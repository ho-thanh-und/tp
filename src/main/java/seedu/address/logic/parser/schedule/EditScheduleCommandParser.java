package seedu.address.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditScheduleCommand object
 */
public class EditScheduleCommandParser implements Parser<EditScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditScheduleCommand
     * and returns an EditScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SCHEDULE, PREFIX_MODE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditScheduleCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SCHEDULE, PREFIX_MODE);

        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();

        if (argMultimap.getValue(PREFIX_SCHEDULE).isPresent()) {
            LocalDate date = ParserUtil.parseDateFromSchedulePrefix(argMultimap.getValue(PREFIX_SCHEDULE).get());
            ArrayList<LocalTime> startEndTime =
                    ParserUtil.parseStartEndTimeFromSchedulePrefix(argMultimap.getValue(PREFIX_SCHEDULE).get());

            editScheduleDescriptor.setDate(date);
            editScheduleDescriptor.setStartTime(startEndTime.get(0));
            editScheduleDescriptor.setEndTime(startEndTime.get(1));
        }
        if (argMultimap.getValue(PREFIX_MODE).isPresent()) {
            editScheduleDescriptor.setMode(ParserUtil.parseMode(argMultimap.getValue(PREFIX_MODE).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditScheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditScheduleCommand(index, editScheduleDescriptor);
    }
}
