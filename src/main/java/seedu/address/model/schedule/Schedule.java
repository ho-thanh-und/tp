package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.ScheduleUtil.checkStartEndDateTime;
import static seedu.address.logic.Messages.MESSAGE_SCHEDULE_START_TIME_BEFORE_END_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Name;

/**
 * Represents a Person's schedule in the schedule board.
 * Guarantees: immutable; is always valid
 */
public class Schedule implements Comparable<Schedule> {

    public static final String MESSAGE_CONSTRAINTS =
            "An interview schedule should contain 4 following details: date, start time, end time, interview mode";
    public static final String MESSAGE_DATE_TIME_CONSTRAINTS =
            "An interview schedule's date and duration should contain 3 following details: "
                    + "date, start time, end time in the format "
                    + "yyyy-MM-dd HH:mm HH:mm (e.g. 2025-05-13 12:00 13:00)";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Mode mode;
    private Name candidateName;
    private Email candidateEmail;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param date A valid interview date.
     * @param startTime A valid interview start time.
     * @param endTime A valid interview end time.
     * @param mode A valid interview mode.
     * @param candidateName A candidate's name.
     * @param candidateEmail A candidate's email.
     */
    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, Mode mode, Name candidateName,
            Email candidateEmail) {
        requireAllNonNull(date, startTime, endTime, mode, candidateName, candidateEmail);
        checkArgument(checkStartEndDateTime(startTime, endTime),
                MESSAGE_SCHEDULE_START_TIME_BEFORE_END_TIME);

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
    }

    /**
     * Constructs a {@code Schedule}.
     *
     * @param date A valid interview date.
     * @param startTime A valid interview start time.
     * @param endTime A valid interview end time.
     * @param mode A valid interview mode.
     */
    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, Mode mode) {
        requireAllNonNull(date, startTime, endTime, mode);

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    /**
     * Constructs a {@code Schedule}.
     *
     * @param date A valid interview date.
     * @param startTime A valid interview start time.
     * @param endTime A valid interview end time.
     */
    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(date, startTime, endTime);
        checkArgument(checkStartEndDateTime(startTime, endTime),
                MESSAGE_SCHEDULE_START_TIME_BEFORE_END_TIME);

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public Mode getMode() {
        return mode;
    }

    public Name getCandidateName() {
        return this.candidateName;
    }

    public Email getCandidateEmail() {
        return this.candidateEmail;
    }

    public void setCandidateEmail(Email email) {
        this.candidateEmail = email;
    }

    public void setCandidateName(Name candidateName) {
        this.candidateName = candidateName;
    }

    /**
     * Returns true if both interview schedules clash,
     * where the startTime of the first interview schedule is after the second
     * interview schedule or vice versa.
     */
    public boolean timeClash(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        if (!this.getDate().equals(otherSchedule.getDate())) {
            return false;
        }
        // Schedules clash if one starts before the other ends and vice versa.
        boolean clash = this.getStartTime().isBefore(otherSchedule.getEndTime())
                && otherSchedule.getStartTime().isBefore(this.getEndTime());
        if (clash) {
            logger.info("Time clash detected between schedules.");
        }
        return clash;
    }

    /**
     * Returns true is the schedule is  in the past.
     */
    public boolean isPast() {
        LocalDateTime scheduleEndDateTime = LocalDateTime.of(this.date, this.endTime);
        return scheduleEndDateTime.isBefore(LocalDateTime.now());
    }

    @Override
    public int compareTo(Schedule other) {
        int dateComparison = this.date.compareTo(other.date);
        return dateComparison != 0 ? dateComparison : this.startTime.compareTo(other.startTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule otherSchedule)) {
            return false;
        }

        return otherSchedule.getDate().equals(getDate())
                && otherSchedule.getStartTime().equals(getStartTime())
                && otherSchedule.getEndTime().equals(getEndTime());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return new ToStringBuilder(this)
                .add("date", date.format(dateFormatter))
                .add("startTime", startTime.format(timeFormatter))
                .add("endTime", endTime.format(timeFormatter))
                .add("mode", mode)
                .toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, startTime, endTime, mode, candidateName, candidateEmail);
    }
}
