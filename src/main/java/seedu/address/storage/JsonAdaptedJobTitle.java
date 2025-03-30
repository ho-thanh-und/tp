package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.JobTitle;

/**
 * Jackson-friendly version of {@link JobTitle}.
 */
class JsonAdaptedJobTitle {

    private final String jobTitleName;

    /**
     * Constructs a {@code JsonAdaptedJobTitle} with the given {@code jobTitleName}.
     */
    @JsonCreator
    public JsonAdaptedJobTitle(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    /**
     * Converts a given {@code JobTitle} into this class for Jackson use.
     */
    public JsonAdaptedJobTitle(JobTitle source) {
        jobTitleName = source.value;
    }

    @JsonValue
    public String getJobTitleName() {
        return jobTitleName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code JobTitle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public JobTitle toModelType() throws IllegalValueException {
        if (!JobTitle.isValidJobTitle(jobTitleName)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(jobTitleName);
    }

}

