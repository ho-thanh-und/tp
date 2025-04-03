package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Theme;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the input to the theme command to change the theme.
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {
    @Override
    public ThemeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Theme theme = ParserUtil.parseTheme(args);
            return new ThemeCommand(theme);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE), pe);
        }
    }

}
