package seedu.address.logic.commands;

import java.util.Map;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.JobTitle;
import seedu.address.model.Model;

public class ViewStatsCommand extends Command {

    public static final String COMMAND_WORD = "viewstats";
    public static final String MESSAGE_SUCCESS = "Statistics:\n%s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Map<JobTitle, Long> stats = model.getAddressBook().getJobApplicantStatistics();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<JobTitle, Long> entry : stats.entrySet()) {
            sb.append(entry.getKey().toString())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb.toString()));
    }
}
