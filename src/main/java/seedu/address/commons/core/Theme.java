package seedu.address.commons.core;

/**
 * Represents the 2 types of themes , dark and light, for the UI.
 */
public enum Theme {
    LIGHT,
    DARK;

    public static final String MESSAGE_CONSTRAINTS =
            "Theme should only be 'light' or 'dark'.";

    /**
     * Checks if {@code test} is a valid string for creating a Theme ENUM.
     */
    public static boolean isValidTheme(String test) {
        return test.equalsIgnoreCase("light") || test.equalsIgnoreCase("dark");
    }

    public boolean isLightTheme() {
        return this.equals(LIGHT);
    }

    public boolean isDarkTheme() {
        return this.equals(DARK);
    }

    @Override
    public String toString() {
        if (isDarkTheme()) {
            return "dark";
        } else {
            return "light";
        }
    }

}
