package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.JobTitle;

/**
 * Adds a new predefined job role.
 */
public class AddJCommand extends Command {
    public static final String COMMAND_WORD = "addJ";
    public static final String MESSAGE_SUCCESS = "New job role added: %s";
    public static final String MESSAGE_DUPLICATE_JOB_ROLE = "This job role already exists.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a predefined job role that matches input.\n"
            + "Parameters: jobTitle \n"
            + "Example: " + COMMAND_WORD + "Software Test Engineer";

    private final JobTitle title;

    /**
     * Creates an AddFCommand to add the specified {@code JobTitle}
     */
    public AddJCommand(JobTitle title) {
        requireNonNull(title);
        this.title = title;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJobTitle(title)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB_ROLE);
        }
        model.addJobTitle(title);
        return new CommandResult(String.format(MESSAGE_SUCCESS, title));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddJCommand)) {
            return false;
        }

        AddJCommand otherAddCommand = (AddJCommand) other;
        return title.equals(otherAddCommand.title);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", title)
                .toString();
    }

}
