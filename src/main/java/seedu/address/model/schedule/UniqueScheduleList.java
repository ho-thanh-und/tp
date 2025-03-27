package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;


/**
 * A list of schedules that enforces uniqueness between its elements and does not allow nulls.
 * A schedule is considered unique by comparing using {@code Schedule#equals(Schedule)}. As such, adding, updating and
 * removal of schedule uses Schedule#equals(Schedule) to ensure that the schedule being added, updated or removed is
 * unique in terms of identity in the UniqueScheduleList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Schedule#equals(Object)
 */
public class UniqueScheduleList implements Iterable<Schedule> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final ObservableList<Schedule> internalList = FXCollections.observableArrayList();
    private final ObservableList<Schedule> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent schedule as the given argument.
     */
    public boolean contains(Schedule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains another schedule which timing clashes with the argument.
     * Used by the add command.
     */
    public boolean hasSameTime(Schedule schedule) {
        requireNonNull(schedule);
        logger.info("" + internalList.stream().filter(schedule::timeClash).count());
        if (internalList.size() == 1) {
            return internalList.stream().filter(schedule::timeClash).count() > 0;
        }
        return internalList.stream().filter(schedule::timeClash).count() > 1;
    }

    /**
     * Returns true if the list contains another schedule which timing clashes with the argument.
     * Used by the edit command.
     */
    public boolean hasSameTimeEdit(Schedule schedule) {
        requireNonNull(schedule);
        logger.info("" + internalList.stream().filter(schedule::timeClash).count());
        return internalList.stream().filter(schedule::timeClash).count() > 1;
    }


    /**
     * Adds a schedule to the list.
     * The schedule must not already exist in the list.
     */
    public void add(Schedule toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateScheduleException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the list.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScheduleNotFoundException();
        }

        if (!target.equals(editedSchedule) && contains(editedSchedule)) {
            throw new DuplicateScheduleException();
        }

        internalList.set(index, editedSchedule);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent schedule from the list.
     * The schedule must exist in the list.
     */
    public void remove(Schedule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ScheduleNotFoundException();
        }
    }

    public void setSchedules(UniqueScheduleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code schedules}.
     * {@code schedules} must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        requireAllNonNull(schedules);
        if (!schedulesAreUnique(schedules)) {
            throw new DuplicateScheduleException();
        }
        internalList.setAll(schedules);
        FXCollections.sort(internalList);
    }

    /**
     * Returns the schedule at the {@code index}.
     * @param index Index of the candidate.
     * @return Schedule at the index.
     */
    public Schedule getSchedule(int index) {
        return internalList.get(index);
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Schedule> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Replaces the candidate info in the schedule {@code target} in the list with the candidate info from
     * {@code editedCandidate}.
     * {@code target} must exist in the list.
     */
    public void editCandidateInSchedule(Schedule target, Person editedCandidate) {
        requireAllNonNull(target, editedCandidate);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScheduleNotFoundException();
        }

        internalList.get(index).setCandidateEmail(editedCandidate.getEmail());
        internalList.get(index).setCandidateName(editedCandidate.getName());
        FXCollections.sort(internalList);
    }

    @Override
    public Iterator<Schedule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueScheduleList // instanceof handles nulls
                && internalList.equals(((UniqueScheduleList) other).internalList));
    }


    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code schedules} contains only unique schedules.
     */
    private boolean schedulesAreUnique(List<Schedule> schedules) {
        return CollectionUtil.isUnique(schedules);
    }

    public ObservableList<Schedule> getAllSchedules() {
        return this.internalList;
    }


}
