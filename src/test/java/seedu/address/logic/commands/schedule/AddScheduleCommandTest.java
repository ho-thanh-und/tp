package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleBoard;
import seedu.address.testutil.ScheduleBuilder;

public class AddScheduleCommandTest {

    @Test
    public void constructor_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_scheduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingScheduleAdded modelStub = new ModelStubAcceptingScheduleAdded();
        Schedule validSchedule = new ScheduleBuilder().build();
        Index index = Index.fromOneBased(1);

        CommandResult commandResult = new AddScheduleCommand(index, validSchedule).execute(modelStub);

        assertEquals(String.format(AddScheduleCommand.MESSAGE_SUCCESS, validSchedule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSchedule), modelStub.schedulesAdded);
    }


    @Test
    public void execute_duplicateSchedule_throwsCommandException() {
        Schedule validSchedule = new ScheduleBuilder().build();
        Index index = Index.fromOneBased(1);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(index, validSchedule);
        ModelStub modelStub = new ModelStubWithSchedule(validSchedule);

        assertThrows(CommandException.class, AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE, () ->
                addScheduleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Schedule firstSchedule = new ScheduleBuilder().withDate("2025-03-15").build();
        Schedule secondSchedule = new ScheduleBuilder().withDate("2025-02-15").build();

        Index index = Index.fromOneBased(2);
        AddScheduleCommand addFirstScheduleCommand = new AddScheduleCommand(index, firstSchedule);
        AddScheduleCommand addSecondScheduleCommand = new AddScheduleCommand(index, secondSchedule);

        // same object -> returns true
        assertTrue(addFirstScheduleCommand.equals(addFirstScheduleCommand));

        // same values -> returns true
        AddScheduleCommand addFirstScheduleCommandCopy = new AddScheduleCommand(index, firstSchedule);
        assertTrue(addFirstScheduleCommand.equals(addFirstScheduleCommandCopy));

        // different types -> returns false
        assertFalse(addFirstScheduleCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstScheduleCommand.equals(null));

        // different schedule -> returns false
        assertFalse(addFirstScheduleCommand.equals(addSecondScheduleCommand));
    }


    /**
     * A default model stub that have all of the methods failing.
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
    }

    /**
     * A Model stub that contains a single schedule.
     */
    private class ModelStubWithSchedule extends ModelStub {
        private final Schedule schedule;
        private final List<Person> persons;

        ModelStubWithSchedule(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
            persons = new ArrayList<>();
            persons.add(ALICE);
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return this.schedule.equals(schedule);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(persons);
        }
    }

    /**
     * A Model stub that always accept the schedule being added.
     */
    private class ModelStubAcceptingScheduleAdded extends ModelStub {
        final ArrayList<Schedule> schedulesAdded = new ArrayList<>();
        private final List<Person> persons = new ArrayList<>(Collections.singletonList(ALICE));


        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return schedulesAdded.stream().anyMatch(schedule::equals);
        }

        @Override
        public void addSchedule(Schedule schedule) {
            requireNonNull(schedule);
            schedulesAdded.add(schedule);
        }

        @Override
        public ReadOnlyScheduleBoard getScheduleBoard() {
            return new ScheduleBoard();
        }

        @Override
        public boolean hasSameDateTime(Schedule schedule) {
            return schedulesAdded.stream().anyMatch(schedule::timeClash);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(persons);
        }
    }

}
