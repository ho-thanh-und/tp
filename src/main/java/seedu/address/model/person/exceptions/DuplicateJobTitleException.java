package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateJobTitleException extends RuntimeException {
    public DuplicateJobTitleException() {
        super("Operation would result in duplicate jobTitles");
    }
}
