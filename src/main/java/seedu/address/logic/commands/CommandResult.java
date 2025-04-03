package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final boolean showNewCandidate;

    private Person person;

    private final Map<JobTitle, Long> statistics;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showNewCandidate,
                         Map<JobTitle, Long> statistics) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showNewCandidate = showNewCandidate;
        this.showHelp = showHelp;
        this.exit = exit;
        this.person = null;
        this.statistics = statistics;
    }

    /**
     * Constructor that does not require statistics.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showNewPerson) {
        this(feedbackToUser, showHelp, exit, showNewPerson, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public void setCandidateToShow(Person person) {
        this.person = person;
    }

    public Person getCandidateToShow() {
        return this.person;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean shouldShowNewCandidateFullDetails() {
        return showNewCandidate;
    }

    public Map<JobTitle, Long> getStatistics() {
        return statistics;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showNewCandidate == otherCommandResult.showNewCandidate
                && Objects.equals(statistics, otherCommandResult.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showNewCandidate, statistics);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("showCandidateFullDetails", showNewCandidate)
                .add("exit", exit)
                .add("statistics", statistics)
                .toString();
    }

}
