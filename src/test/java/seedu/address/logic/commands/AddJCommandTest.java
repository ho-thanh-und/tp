package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobTitles.JOB_TITLE_NOT_IN_DEFAULT_LIST;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Theme;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.Schedule;

public class AddJCommandTest {

    @Test
    public void constructor_nullJobTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddJCommand(null));
    }

    @Test
    public void execute_jobTitleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingJobTitleAdded modelStub = new ModelStubAcceptingJobTitleAdded();
        AddJCommand addJCommand = new AddJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);

        CommandResult commandResult = addJCommand.execute(modelStub);

        String expectedMessage = String.format(AddJCommand.MESSAGE_SUCCESS, JOB_TITLE_NOT_IN_DEFAULT_LIST);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(JOB_TITLE_NOT_IN_DEFAULT_LIST), modelStub.jobTitleAdded);
    }

    @Test
    public void execute_duplicateJobTitle_throwsCommandException() {
        ModelStubWithJobTitle modelStub = new ModelStubWithJobTitle(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        AddJCommand addJCommand = new AddJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        assertThrows(CommandException.class, () -> addJCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        JobTitle anothorJobTitle = new JobTitle("HR Manager");
        AddJCommand addValidJCommand = new AddJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        AddJCommand addInvalidJCommand = new AddJCommand(anothorJobTitle);

        // same object -> returns true
        assertTrue(addValidJCommand.equals(addValidJCommand));

        // same values -> returns true
        AddJCommand addValidCommandCopy = new AddJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        assertTrue(addValidJCommand.equals(addValidCommandCopy));

        // different types -> returns false
        assertFalse(addValidJCommand.equals(1));

        // null -> returns false
        assertFalse(addValidJCommand.equals(null));

        // different job title -> returns false
        assertFalse(addValidJCommand.equals(addInvalidJCommand));
    }

    @Test
    public void toStringMethod() {
        AddJCommand addJCommand = new AddJCommand(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        String expected = AddJCommand.class.getCanonicalName() + "{toAdd=" + JOB_TITLE_NOT_IN_DEFAULT_LIST + "}";
        assertEquals(expected, addJCommand.toString());
    }

    /**
     * A Model stub that has a job title already present.
     */
    private class ModelStubWithJobTitle extends ModelStub {
        private final JobTitle jobTitle;

        ModelStubWithJobTitle(JobTitle jobTitle) {
            requireNonNull(jobTitle);
            this.jobTitle = jobTitle;
        }

        @Override
        public boolean hasJobTitle(JobTitle jobTitle) {
            requireNonNull(jobTitle);
            return this.jobTitle.equals(jobTitle);
        }
    }

    /**
     * A Model stub that accepts the job title being added.
     */
    private class ModelStubAcceptingJobTitleAdded extends ModelStub {
        final ArrayList<JobTitle> jobTitleAdded = new ArrayList<>();

        @Override
        public boolean hasJobTitle(JobTitle jobTitle) {
            requireNonNull(jobTitle);
            return jobTitleAdded.stream().anyMatch(jobTitle::equals);
        }

        @Override
        public void addJobTitle(JobTitle jobTitle) {
            jobTitleAdded.add(jobTitle);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A default Model stub that have all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<seedu.address.model.person.Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getFirstPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(Schedule scheduleToEdit, Schedule editedSchedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editCandidateInSchedule(Schedule target, Person editedCandidate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameDateTime(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameDateTimeEdit(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyScheduleBoard getScheduleBoard() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setScheduleBoard(ReadOnlyScheduleBoard readOnlyScheduleBoard) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasJobTitle(JobTitle jobTitle) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteJobTitle(JobTitle target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addJobTitle(JobTitle jobTitle) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<JobTitle> getFilteredJobTitleList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Theme getTheme() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setTheme(Theme theme) {
            throw new AssertionError("This method should not be called");
        }
    }
}
