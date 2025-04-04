package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate JobRoles
 */
public class DuplicateJobRoleException extends RuntimeException {
    public DuplicateJobRoleException() {
        super("Operation would result in duplicate jobRoles");
    }
}
