package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.JobRole;

/**
 * Deletes a specified job role from saved list of job roles.
 */
public class DeleteJCommand extends Command {
    public static final String COMMAND_WORD = "deleteJ";
    public static final String MESSAGE_SUCCESS = "Job role deleted: %s";
    public static final String MESSAGE_JOB_ROLE_NOT_FOUND = "Error: Job role not found: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes input job role from saved list.\n"
            + "Parameters: jobRole \n"
            + "Example: " + COMMAND_WORD + " Software Engineer";

    private final JobRole title;

    /**
     * Creates an DeleteJCommand to delete the specified {@code JobRole}
     */
    public DeleteJCommand(JobRole title) {
        this.title = title;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasJobRole(title)) {
            throw new CommandException(String.format(MESSAGE_JOB_ROLE_NOT_FOUND, title));
        }
        model.deleteJobRoles(title);
        return new CommandResult(String.format(MESSAGE_SUCCESS, title));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteJCommand)) {
            return false;
        }

        DeleteJCommand otherDeleteJCommand = (DeleteJCommand) other;
        return title.equals(otherDeleteJCommand.title);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetJobRole", title)
                .toString();
    }
}
