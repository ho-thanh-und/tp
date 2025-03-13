package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a persons Label as Unreviewed, Accepted, Rejected or Shortlisted
 */
public class Label {

    public static final String MESSAGE_CONSTRAINTS =
            "Labels can only be Unreviewed (U), Accepted (A), Rejected (R) or Shortlisted(S)";

    public static final String VALIDATION_REGEX =  "^(Unreviewed|Accepted|Rejected|Shortlisted)$";

    public final String labelName;
    public Label(String labelname) {
        requireNonNull(labelname);
        checkArgument(isValidLabel(labelname), MESSAGE_CONSTRAINTS);
        this.labelName = labelname;

    }

    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "[ " + this.labelName + " ]";
    }

    @Override
    public int hashCode() {
        return this.labelName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Label)) {
            return false;
        }

        Label otherName = (Label) other;
        return labelName.equals(otherName.labelName);
    }

}
