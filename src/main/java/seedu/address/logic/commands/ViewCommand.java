package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    // Updated message for viewing a person.
    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing Person: %1$s";

    public final int targetIndex;

    public ViewCommand(Index index) {
        this.targetIndex = index.getZeroBased();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // ...existing code if any...
        java.util.List<seedu.address.model.person.Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex >= lastShownList.size()) {
            throw new CommandException("Invalid person index");
        }

        seedu.address.model.person.Person selectedPerson = lastShownList.get(targetIndex);
        model.setSelectedPerson(selectedPerson);
        return new CommandResult(
            String.format(MESSAGE_VIEW_PERSON_SUCCESS, selectedPerson.getName()),
            false, false, targetIndex);
    }
}
