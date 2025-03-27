package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.model.schedule.Schedule;

/**
 * A utility class for Schedule.
 */
public class ScheduleUtil {

    /**
     * Returns an add command string for adding the {@code schedule}.
     */
    public static String getAddScheduleCommand(Schedule schedule) {
        return AddScheduleCommand.COMMAND_WORD + " " + getScheduleDetails(schedule);
    }

    /**
     * Returns the part of command string for the given {@code schedule}'s details.
     */
    public static String getScheduleDetails(Schedule schedule) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CANDIDATE + "1" + " ");
        sb.append(PREFIX_SCHEDULE + schedule.getDate().toString() + " " + schedule.getStartTime() + " "
                + schedule.getEndTime() + " ");
        sb.append(PREFIX_MODE + schedule.getMode().toString() + " ");
        return sb.toString();
    }

}
