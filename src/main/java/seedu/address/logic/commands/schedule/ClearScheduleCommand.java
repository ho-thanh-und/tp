package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.schedule.ScheduleBoard;

/**
 * Clears the schedule board.
 */
public class ClearScheduleCommand extends Command {

    public static final String COMMAND_WORD = "clearSchedules";
    public static final String MESSAGE_SUCCESS = "Interview schedule board has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setScheduleBoard(new ScheduleBoard());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
