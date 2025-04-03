package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;

public class JsonAdaptedScheduleTest {
    private static final String INVALID_CANDIDATE_NAME = "@@@";
    private static final String INVALID_CANDIDATE_EMAIL = "example.com";
    private static final String INVALID_DATE = "2222-22";
    private static final String INVALID_START_TIME = "-1:-1";
    private static final String INVALID_END_TIME = "-1:-1";
    private static final String INVALID_MODE = " ";

    private static final String VALID_DATE = SCHEDULE_1.getDate().toString();
    private static final String VALID_START_TIME = SCHEDULE_1.getStartTime().toString();
    private static final String VALID_END_TIME = SCHEDULE_1.getEndTime().toString();
    private static final String VALID_MODE = SCHEDULE_1.getMode().toString();
    private static final String VALID_CANDIDATE_NAME = SCHEDULE_1.getCandidateName().toString();
    private static final String VALID_CANDIDATE_EMAIL = SCHEDULE_1.getCandidateEmail().toString();


    @Test
    public void toModelType_validPersonDetails_returnsSchedule() throws Exception {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(SCHEDULE_1);
        assertEquals(SCHEDULE_1, schedule.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(INVALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = ParserUtil.MESSAGE_INVALID_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(null, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = String.format(JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT, "INTERVIEW DATE");
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, INVALID_START_TIME, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = ParserUtil.MESSAGE_INVALID_TIME;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullStarTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, null, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = String.format(JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT,
                "INTERVIEW START TIME");
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, INVALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = ParserUtil.MESSAGE_INVALID_TIME;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, null,
                VALID_MODE, VALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = String.format(JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT, "INTERVIEW END TIME");
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidCandidateName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, INVALID_CANDIDATE_NAME, VALID_CANDIDATE_EMAIL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullCandidateName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, null, VALID_CANDIDATE_EMAIL);
        String expectedMessage = String.format(JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_invalidCandidateEmail_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, INVALID_CANDIDATE_EMAIL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }

    @Test
    public void toModelType_nullCandidateEmail_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_DATE, VALID_START_TIME, VALID_END_TIME,
                VALID_MODE, VALID_CANDIDATE_NAME, null);
        String expectedMessage = String.format(JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT,
                Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, schedule::toModelType);
    }
}
