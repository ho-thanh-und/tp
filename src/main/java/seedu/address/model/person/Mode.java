package seedu.address.model.person;

/**
 * Represents the enum Mode.
 */
public enum Mode {
    ONLINE,
    OFFLINE;

    public static final String MESSAGE_CONSTRAINTS =
            "Mode should only be either 'online' or 'offline'.";

    /**
     * Returns true if the given string is a valid mode.
     */
    public static boolean isValidMode(String test) {
        return test.equalsIgnoreCase("online") || test.equalsIgnoreCase("offline");
    }
}
