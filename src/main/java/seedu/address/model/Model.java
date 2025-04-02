package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.Schedule;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns the first person in the list; if list is empty, null is returned
     */
    Person getFirstPerson();

    //=========== Schedule operations =============================================================

    /**
     * Returns true if a schedule with the same identity as {@code schedule} exists in schedule board.
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * Adds the given schedule.
     * {@code schedule} must not already exist in schedule board.
     */
    void addSchedule(Schedule schedule);

    /**
     * Deletes the given schedule.
     * The schedule must exist in schedule board.
     */
    void deleteSchedule(Schedule target);

    /**
     * Replaces the given schedule {@code scheduleToEdit} with {@code editedSchedule}.
     * {@code scheduleToEdit} must exist in the schedule board.
     * The candidate info of {@code editedSchedule} must not be the same as another existing schedule in schedule board.
     */
    void setSchedule(Schedule scheduleToEdit, Schedule editedSchedule);

    /**
     * Edits the candidate identified in a given interview schedule.
     * The schedule must exist in schedule board.
     */
    void editCandidateInSchedule(Schedule target, Person editedCandidate);

    /** Returns an unmodifiable view of the filtered schedule list */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    /**
     * Returns true if a {@code schedule}'s timing clashes with another {@code schedule}'s timing in board.
     */
    boolean hasSameDateTime(Schedule schedule);

    /**
     * Returns true if the list contains another schedule which timing clashes with the argument.
     * Used by the editSchedule command.
     */
    boolean hasSameDateTimeEdit(Schedule editedSchedule, Schedule scheduleToEdit);


    /** Returns the Schedule Board */
    ReadOnlyScheduleBoard getScheduleBoard();

    void setScheduleBoard(ReadOnlyScheduleBoard scheduleBoard);
}
