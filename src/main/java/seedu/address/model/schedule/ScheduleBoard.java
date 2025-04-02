package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Organizes all schedule data at the Schedule Board level
 * Duplicate scheduled are prohibited
 */
public class ScheduleBoard implements ReadOnlyScheduleBoard {

    private final UniqueScheduleList schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        schedules = new UniqueScheduleList();
    }

    public ScheduleBoard() {}

    /**
     * Creates a ScheduleBoard using the schedules in the {@code toBeCopied}
     */
    public ScheduleBoard(ReadOnlyScheduleBoard toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Creates a ScheduleBoard using the schedules in the {@code scheduleList}
     */
    public ScheduleBoard(List<Schedule> scheduleList) {
        this.schedules.setSchedules(scheduleList);
    }

    /**
     * Replaces the contents of the schedule list with {@code schedules}.
     * {@code schedules} must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setSchedules(schedules);
    }

    /**
     * Resets the existing data of this {@code ScheduleBoard} with {@code newData}.
     */
    public void resetData(ReadOnlyScheduleBoard newData) {
        requireNonNull(newData);

        setSchedules(newData.getScheduleList());
    }

    /**
     * Returns true if a schedule with the same identity as {@code schedule} exists in TAble.
     */
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return schedules.contains(schedule);
    }

    /**
     * Adds a schedule to TAble.
     * The schedule must not already exist in TAble.
     */
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    /**
     * Replaces the given schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the ScheduleBoard.
     * The candidate identity of {@code editedSchedule} must not be the same
     * as another current candidate in the ScheduleBoard.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);

        schedules.setSchedule(target, editedSchedule);
    }

    /**
     * Removes {@code key} from this {@code ScheduleBoard}.
     * {@code key} must exist in the ScheduleBoard.
     */
    public void removeSchedule(Schedule key) {
        schedules.remove(key);
    }


    /**
     * Returns {@code schedule} from {@code Schedule Board}.
     * {@code schedule} must exist in ScheduleBoard.
     */
    public Schedule getSchedule(int index) {
        return schedules.getSchedule(index);
    }

    /**
     * Replaces the candidate info in the given schedule {@code target} with the identity of the
     * edited candidate {@code editedSchedule}.
     * {@code target} must exist in the ScheduleBoard.
     */
    public void editCandidateInSchedule(Schedule schedule, Person editedCandidate) {
        schedules.editCandidateInSchedule(schedule, editedCandidate);
    }



    public boolean hasSameDateTime(Schedule schedule) {
        return schedules.hasSameTime(schedule);
    }

    public boolean hasSameDateTimeEdit(Schedule schedule) {
        return schedules.hasSameTimeEdit(schedule);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Schedules", schedules)
                .toString();
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleBoard // instanceof handles nulls
                && schedules.equals(((ScheduleBoard) other).schedules));
    }

    @Override
    public int hashCode() {
        return schedules.hashCode();
    }
}
