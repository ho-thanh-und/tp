package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CANDIDATES_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULES_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ScheduleBoard;
import seedu.address.storage.ManualStorage;

public class SaveCommandTest {
    private static final String TEMP_CANDIDATES_FILE_NAME = "temporary_candidates_test.txt";
    private static final String TEMP_SCHEDULES_FILE_NAME = "temporary_schedules_test.txt";

    private Path validCandidatesFilePath;
    private Path validSchedulesFilePath;
    private Model model;

    @BeforeEach
    public void setUp() {
        this.validCandidatesFilePath = Path.of(VALID_CANDIDATES_FILE_PATH);
        this.validSchedulesFilePath = Path.of(VALID_SCHEDULES_FILE_PATH);
        this.model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ScheduleBoard());
    }

    @Test
    public void execute_candidateFileExistsAndShouldNotBeOverwritten_failure() throws IOException {
        FileUtil.createIfMissing(validCandidatesFilePath);
        File file = validCandidatesFilePath.toFile();

        // Empty path provided for schedules file path
        SaveCommand saveCommand = new SaveCommand(validCandidatesFilePath, ManualStorage.EMPTY_PATH, false, false);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, validCandidatesFilePath.toAbsolutePath()));

        // Non-empty path provided for schedules file path
        saveCommand = new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, false);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, validCandidatesFilePath.toAbsolutePath()));

        file.delete();
    }

    @Test
    public void execute_scheduleFileExistsAndShouldNotBeOverwritten_failure() throws IOException {
        FileUtil.createIfMissing(validSchedulesFilePath);
        File file = validSchedulesFilePath.toFile();

        // Empty path provided for candidates file path
        SaveCommand saveCommand = new SaveCommand(ManualStorage.EMPTY_PATH, validSchedulesFilePath, false, false);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, validSchedulesFilePath.toAbsolutePath()));

        // Non-empty path provided for candidates file path
        saveCommand = new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, false);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, validSchedulesFilePath.toAbsolutePath()));

        file.delete();
    }

    @Test
    public void execute_bothFileExistsAndShouldNotBeOverwritten_failure() throws IOException {
        FileUtil.createIfMissing(validCandidatesFilePath);
        File candidatesFile = validCandidatesFilePath.toFile();
        FileUtil.createIfMissing(validSchedulesFilePath);
        File schedulesFile = validSchedulesFilePath.toFile();

        SaveCommand saveCommand = new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, false);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, validCandidatesFilePath.toAbsolutePath()));

        candidatesFile.delete();
        schedulesFile.delete();
    }

    @Test
    public void execute_fileDoesNotExistAndShouldNotBeOverwritten_success() throws IOException {
        File file = validCandidatesFilePath.toFile();
        File tempNewFile = Path.of(VALID_FOLDER_PATH, TEMP_CANDIDATES_FILE_NAME).toFile();
        boolean haveMovedFile = false;

        if (file.exists()) {
            haveMovedFile = true;
            file.renameTo(tempNewFile);
        }

        SaveCommand saveCommand = new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, true);
        String expectedMessage = String.format(
                SaveCommand.MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, this.validCandidatesFilePath.toAbsolutePath())
                + System.lineSeparator()
                + String.format(
                SaveCommand.MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, this.validSchedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());
        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        if (haveMovedFile) {
            tempNewFile.renameTo(file);
        }
    }

    @Test
    public void execute_fileExistsAndCanBeOverwritten_success() throws IOException {
        FileUtil.createIfMissing(validCandidatesFilePath);
        File file = validCandidatesFilePath.toFile();

        SaveCommand saveCommand = new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, true);
        String expectedMessage = String.format(
                SaveCommand.MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, this.validCandidatesFilePath.toAbsolutePath())
                + System.lineSeparator()
                + String.format(
                        SaveCommand.MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, this.validSchedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());
        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        file.delete();
    }

    @Test
    public void equals() {
        final SaveCommand standardCommand = new SaveCommand(
                validCandidatesFilePath, validSchedulesFilePath, false, false);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // same values -> equal
        SaveCommand commandWithSameValues = new SaveCommand(
                validCandidatesFilePath, validSchedulesFilePath, false, false);
        assertEquals(standardCommand, commandWithSameValues);

        // null -> no equal
        assertNotEquals(null, standardCommand);

        // different types -> not equal
        assertNotEquals(new ClearCommand(), standardCommand);

        // different path -> not equal
        assertNotEquals(new SaveCommand(
                Path.of("/" + VALID_CANDIDATES_FILE_PATH), validSchedulesFilePath, false, false),
                standardCommand);
        assertNotEquals(new SaveCommand(
                validCandidatesFilePath, Path.of("/" + VALID_SCHEDULES_FILE_PATH), false, false),
                standardCommand);

        // same path but different format (i.e., relative and absolute) -> equal
        assertEquals(new SaveCommand(validCandidatesFilePath.toAbsolutePath(), validSchedulesFilePath, false, false),
                standardCommand);
        assertEquals(new SaveCommand(validCandidatesFilePath, validSchedulesFilePath.toAbsolutePath(), false, false),
                standardCommand);

        // same path but different options -> not equal
        assertNotEquals(new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, true, false), standardCommand);
        assertNotEquals(new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, false, true), standardCommand);
        assertNotEquals(new SaveCommand(validCandidatesFilePath, validSchedulesFilePath, true, true), standardCommand);
    }
}
