package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    private final String prefixField;

    /**
     * Constructs a prefix using {@code prefix} to indicate the prefix and
     * {@code prefixField} to indicate what the prefix refers to.
     *
     * @param prefix
     * @param prefixField
     */
    public Prefix(String prefix, String prefixField) {
        this.prefix = prefix;
        this.prefixField = prefixField;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPrefixField() {
        return prefixField;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix)
                && prefixField.equals(otherPrefix.prefixField);
    }
}
