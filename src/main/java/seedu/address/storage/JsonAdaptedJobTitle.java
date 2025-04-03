package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.JobTitle;

/**
 * Jackson-friendly version of {@link JobTitle}.
 */
class JsonAdaptedJobTitle {

    private final String jobTitle;

    /**
     */
    @JsonCreator
    public JsonAdaptedJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Converts a given {@code JobTitle} into this class for Jackson use.
     */
    public JsonAdaptedJobTitle(JobTitle source) {
        jobTitle = source.value;
    }

    @JsonValue
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Converts this Jackson-friendly adapted jobTitle object into the model's {@code JobTitle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted jobTitle.
     */
    public JobTitle toModelType() throws IllegalValueException {
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_NEW_CONSTRAINTS);
        }
        return new JobTitle(jobTitle);
    }

}
