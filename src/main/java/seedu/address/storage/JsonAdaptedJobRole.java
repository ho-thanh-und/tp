package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.JobRole;

/**
 * Jackson-friendly version of {@link JobRole}.
 */
class JsonAdaptedJobRole {

    private final String jobRole;

    /**
     * Constructs a {@code JsonAdaptedJobRole} with the given {@code value}.
     */
    @JsonCreator
    public JsonAdaptedJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    /**
     * Converts a given {@code JobRole} into this class for Jackson use.
     */
    public JsonAdaptedJobRole(JobRole source) {
        jobRole = source.value;
    }

    @JsonValue
    public String getJobRole() {
        return jobRole;
    }

    /**
     * Converts this Jackson-friendly adapted jobRole object into the model's {@code JobRole} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted jobRole.
     */
    public JobRole toModelType() throws IllegalValueException {
        if (!JobRole.isValidJobRole(jobRole)) {
            throw new IllegalValueException(JobRole.MESSAGE_NEW_CONSTRAINTS);
        }
        return new JobRole(jobRole);
    }

}
