package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.Theme;
import seedu.address.commons.util.ToStringBuilder;
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

    private final boolean hasThemeChanged;

    private Theme theme;

    private Person person;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showNewCandidate,
                         boolean hasThemeChanged) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showNewCandidate = showNewCandidate;
        this.showHelp = showHelp;
        this.exit = exit;
        this.person = null;
        this.hasThemeChanged = hasThemeChanged;

    }
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showNewCandidate) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showNewCandidate = showNewCandidate;
        this.showHelp = showHelp;
        this.exit = exit;
        this.person = null;
        this.hasThemeChanged = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false,
                false, false);
    }

    /**
     * An alternate constructor for {@code CommandResult} without needing to specify
     * {@code changeTheme} (by default set to false).
     */

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public void setCandidateToShow(Person person) {
        this.person = person;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return this.theme;
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

    public boolean shouldChangeTheme() {
        return this.hasThemeChanged;
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
                && hasThemeChanged == otherCommandResult.hasThemeChanged
                && showNewCandidate == otherCommandResult.showNewCandidate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showNewCandidate, hasThemeChanged);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("showCandidateFullDetails", showNewCandidate)
                .add("exit", exit)
                .add("changeTheme", hasThemeChanged)
                .toString();
    }

}
