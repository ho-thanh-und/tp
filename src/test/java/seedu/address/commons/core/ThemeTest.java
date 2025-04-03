package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class ThemeTest {
    @Test
    public void toStringMethod() {
        assertEquals(Theme.DARK.toString(), "dark");
        assertEquals(Theme.LIGHT.toString(), "light");
    }

    @Test
    public void equalsMethod() {
        Theme themeDark = Theme.DARK;
        Theme themeLight = Theme.LIGHT;

        assertTrue(themeDark.equals(themeDark));
        assertTrue(themeLight.equals(themeLight));

        assertFalse(themeDark.equals(themeLight));

    }

    @Test
    public void isMethods() {
        Theme themeDark = Theme.DARK;
        Theme themeLight = Theme.LIGHT;

        assertTrue(themeDark.isDarkTheme());
        assertTrue(themeLight.isLightTheme());

        assertFalse(themeDark.isLightTheme());
        assertFalse(themeLight.isDarkTheme());
    }

    @Test
    public void isValidThemeMethod() {
        assertTrue(Theme.isValidTheme("dark"));
        assertTrue(Theme.isValidTheme("DaRk"));
        assertTrue(Theme.isValidTheme("light"));
        assertTrue(Theme.isValidTheme("LiGhT"));

        assertFalse(Theme.isValidTheme("daasdaksj"));
    }

    @Test
    public void stringToThemeMethod() throws IllegalValueException {
        Theme themeDark = Theme.DARK;
        Theme themeLight = Theme.LIGHT;

        assertEquals(Theme.stringToTheme("dark"), themeDark);
        assertEquals(Theme.stringToTheme("Light"), themeLight);

        assertThrows(IllegalValueException.class, () -> Theme.stringToTheme("aaa"));

    }
}
