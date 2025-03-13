package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Schedule;
import seedu.address.testutil.PersonBuilder;

public class ScheduleCommandTest {
    private static final String Schedule_STUB = "Some Schedule";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addScheduleUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withSchedule(Schedule_STUB).build();

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(editedPerson.getSchedule().value));

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_ADD_SCHEDULE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteScheduleUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withSchedule("").build();

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(editedPerson.getSchedule().toString()));

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withSchedule(Schedule_STUB).build();

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(editedPerson.getSchedule().value));

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_ADD_SCHEDULE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, new Schedule(VALID_SCHEDULE_BOB));

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, new Schedule(VALID_SCHEDULE_BOB));

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ScheduleCommand standardCommand = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(VALID_SCHEDULE_AMY));

        // same values -> returns true
        ScheduleCommand commandWithSameValues = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(VALID_SCHEDULE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(INDEX_SECOND_PERSON,
                new Schedule(VALID_SCHEDULE_AMY))));

        // different Schedule -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(INDEX_FIRST_PERSON,
                new Schedule(VALID_SCHEDULE_BOB))));
    }
}
