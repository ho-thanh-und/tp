package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.Schedule;


/**
 * Jackson-friendly version of {@link seedu.address.model.person.Person}.
 */
class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String date;
    private final String startTime;
    private final String endTime;
    private final String mode;
    private final String candidateName;
    private final String candidateEmail;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("date") String date, @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime, @JsonProperty("mode") String mode,
            @JsonProperty("candidateName") String candidateName,
            @JsonProperty("candidateEmail") String candidateEmail) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        date = source.getDate().toString();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        mode = source.getMode().toString();
        candidateName = source.getCandidateName().toString();
        candidateEmail = source.getCandidateEmail().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Schedule toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "INTERVIEW DATE"));
        }

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "INTERVIEW START TIME"));
        }

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "INTERVIEW END TIME"));
        }
        final LocalDate modelDate;

        try {
            modelDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(ParserUtil.MESSAGE_INVALID_DATE);
        }

        final LocalTime modelStartTime;
        final LocalTime modelEndTime;

        try {
            modelStartTime = LocalTime.parse(startTime);
            modelEndTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(ParserUtil.MESSAGE_INVALID_TIME);
        }

        if (mode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Mode.class.getSimpleName()));
        }

        if (!Mode.isValidMode(mode)) {
            throw new IllegalValueException(Mode.MESSAGE_CONSTRAINTS);
        }

        final Mode modelMode = mode.equalsIgnoreCase("online") ? Mode.ONLINE : Mode.OFFLINE;

        if (candidateName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(candidateName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelCandidateName = new Name(candidateName);


        if (candidateEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(candidateEmail)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelCandidateEmail = new Email(candidateEmail);

        return new Schedule(modelDate, modelStartTime, modelEndTime,
                modelMode, modelCandidateName, modelCandidateEmail);
    }

}
