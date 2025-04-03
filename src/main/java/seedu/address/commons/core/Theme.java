package seedu.address.commons.core;

import seedu.address.commons.exceptions.IllegalValueException;

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

    /**
     * Returns a {@code Theme} for a given {@code String}.
     */
    public static Theme stringToTheme(String trimmedString) throws IllegalValueException {
        if (isValidTheme(trimmedString)) {
            return trimmedString.equalsIgnoreCase("dark") ? Theme.DARK : Theme.LIGHT;
        } else {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
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
