package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Email;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.Schedule;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {

    public static final LocalDate DEFAULT_DATE =
            LocalDate.parse("2025-03-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalTime DEFAULT_START_TIME =
            LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"));
    public static final LocalTime DEFAULT_END_TIME =
            LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm"));
    public static final Mode DEFAULT_MODE = Mode.ONLINE;
    public static final String DEFAULT_CANDIDATE_NAME = "Amy";
    public static final String DEFAULT_CANDIDATE_EMAIL = "amy@gmail.com";

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Mode mode;
    private Name candidateName;
    private Email candidateEmail;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        date = DEFAULT_DATE;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        mode = DEFAULT_MODE;
        candidateName = new Name(DEFAULT_CANDIDATE_NAME);
        candidateEmail = new Email(DEFAULT_CANDIDATE_EMAIL);
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        date = scheduleToCopy.getDate();
        startTime = scheduleToCopy.getStartTime();
        endTime = scheduleToCopy.getEndTime();
        mode = scheduleToCopy.getMode();
        candidateName = scheduleToCopy.getCandidateName();
        candidateEmail = scheduleToCopy.getCandidateEmail();
    }

    /**
     * Sets the {@code date} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withStartTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.startTime = LocalTime.parse(startTime, formatter);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withEndTime(String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.endTime = LocalTime.parse(endTime, formatter);
        return this;
    }

    /**
     * Sets the {@code Mode} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withMode(String mode) {
        this.mode = mode.equalsIgnoreCase("online") ? Mode.ONLINE : Mode.OFFLINE;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ScheduleBuilder withCandidateName(String name) {
        this.candidateName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public ScheduleBuilder withCandidateEmail(String email) {
        this.candidateEmail = new Email(email);
        return this;
    }


    /**
     * Builds the {@code Schedule} and returns it
     */
    public Schedule build() {
        return new Schedule(date, startTime, endTime, mode, candidateName, candidateEmail);
    }

}

