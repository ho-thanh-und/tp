package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Theme;
import seedu.address.model.person.JobRole;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleBoard;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ScheduleBoard scheduleBoard;
    private final FilteredList<Schedule> filteredSchedules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
            ReadOnlyScheduleBoard scheduleBoard) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
            + " and interview schedules " + scheduleBoard);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.scheduleBoard = new ScheduleBoard(scheduleBoard);
        filteredSchedules = new FilteredList<>(this.scheduleBoard.getScheduleList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new ScheduleBoard());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Person getFirstPerson() {
        if (this.getFilteredPersonList().isEmpty()) {
            return null;
        }
        return this.getFilteredPersonList().get(0);
    }

    //=========== Schedule =============================================================
    @Override
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return scheduleBoard.hasSchedule(schedule);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleBoard.addSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Schedule target) {
        scheduleBoard.removeSchedule(target);
    }

    @Override
    public void setSchedule(Schedule scheduleToEdit, Schedule editedSchedule) {
        requireAllNonNull(scheduleToEdit, editedSchedule);
        scheduleBoard.setSchedule(scheduleToEdit, editedSchedule);
    }

    @Override
    public void editCandidateInSchedule(Schedule schedule, Person editedCandidate) {
        scheduleBoard.editCandidateInSchedule(schedule, editedCandidate);
    }

    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedules;
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredSchedules.setPredicate(predicate);
    }

    @Override
    public boolean hasSameDateTime(Schedule schedule) {
        requireAllNonNull(schedule, scheduleBoard);
        return scheduleBoard.hasSameDateTime(schedule);
    }


    @Override
    public boolean hasSameDateTimeEdit(Schedule schedule, Schedule scheduleToEdit) {
        requireAllNonNull(schedule, scheduleBoard);
        return scheduleBoard.hasSameDateTimeEdit(schedule, scheduleToEdit);
    }

    @Override
    public ReadOnlyScheduleBoard getScheduleBoard() {
        return scheduleBoard;
    }

    @Override
    public void setScheduleBoard(ReadOnlyScheduleBoard scheduleBoard) {
        this.scheduleBoard.resetData(scheduleBoard);
    }

    @Override
    public Path getScheduleBoardFilePath() {
        return userPrefs.getScheduleBoardFilePath();
    }

    @Override
    public void setScheduleBoardFilePath(Path scheduleBoardFilePath) {
        requireNonNull(scheduleBoardFilePath);
        userPrefs.setScheduleBoardFilePath(scheduleBoardFilePath);
    }
    @Override
    public Theme getTheme() {
        return this.getGuiSettings().getTheme();
    }

    @Override
    public void setTheme(Theme theme) {
        this.getGuiSettings().setTheme(theme);
    }

    //=========== JobRoleList Accessors =============================================================

    @Override
    public boolean hasJobRole(JobRole jobRole) {
        requireNonNull(jobRole);
        return addressBook.hasJobRole(jobRole);
    }

    @Override
    public boolean hasJobRoles(Set<JobRole> jobRoles) {
        requireNonNull(jobRoles);
        return !addressBook.hasJobRoles(jobRoles);
    }

    @Override
    public void deleteJobRoles(JobRole target) {
        addressBook.removeJobRole(target);
    }

    @Override
    public void addJobRole(JobRole jobRole) {
        addressBook.addJobRole(jobRole);
    }

    @Override
    public ObservableList<JobRole> getFilteredJobRolesList() {
        return addressBook.getJobRoleList();
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredSchedules.equals(otherModelManager.filteredSchedules);
    }
}
