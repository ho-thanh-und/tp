package seedu.address.logic.commands;

import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.JobRole;

/**
 * Returns number of job applications grouped by their job role.
 * This command aggregates the number of applicants by their job role
 * and displays the results in a formatted string.
 */
public class ViewStatsCommand extends Command {

    public static final String COMMAND_WORD = "viewstats";
    public static final String MESSAGE_SUCCESS = "Statistics Tab Opened";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Map<JobRole, Long> stats = model.getAddressBook().getJobApplicantStatistics();
        StringBuilder sb = new StringBuilder();
        if (stats.isEmpty()) {
            sb.append("(No existing applications at the moment)");
        } else {
            for (Map.Entry<JobRole, Long> entry : stats.entrySet()) {
                sb.append(entry.getKey().toString())
                        .append(": ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, sb.toString()),
                false,
                false,
                false,
                false,
                stats
        );
    }
}
