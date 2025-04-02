package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Theme;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;



/**
 * Changes the theme of the application to light or dark.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":Changes the theme of the UI to match set theme.\n"
            + "Parameters: light or dark"
            + "Example: " + COMMAND_WORD + " light ";

    public static final String MESSAGE_THEME_CHANGE_SUCCESS = "Changed theme to: %1$s";

    public final Theme theme;

    /**
     * Constructs a {@code} ThemeCommand.
     */
    public ThemeCommand(Theme theme) {
        requireNonNull(theme);
        this.theme = theme;
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {

        CommandResult result = new CommandResult(
                String.format(MESSAGE_THEME_CHANGE_SUCCESS, theme),
                false, false, false, true);

        result.setTheme(theme);
        model.setTheme(theme);

        return result;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ThemeCommand)) {
            return false;
        }
        ThemeCommand otherThemeCommand = (ThemeCommand) other;
        return theme == otherThemeCommand.theme;
    }
}
