package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CANDIDATES_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULES_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SaveCommand.MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS;
import static seedu.address.logic.commands.SaveCommand.MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS;
import static seedu.address.storage.ManualStorage.EMPTY_PATH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ScheduleBoard;

public class SaveCommandTest {
    private Path validCandidatesFilePath;
    private Path validSchedulesFilePath;
    private Path emptyPath;
    private Model model;

    @BeforeEach
    public void setUp() {
        this.validCandidatesFilePath = Path.of(VALID_CANDIDATES_FILE_PATH);
        this.validSchedulesFilePath = Path.of(VALID_SCHEDULES_FILE_PATH);
        this.emptyPath = EMPTY_PATH;
        this.model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new ScheduleBoard());

        File testFolder = Path.of(VALID_FOLDER_PATH).toFile();
        if (!testFolder.exists()) {
            testFolder.mkdir();
        }
    }

    @AfterEach
    public void cleanUp() {
        File testFolder = Path.of(VALID_FOLDER_PATH).toFile();
        if (testFolder.exists()) {
            Arrays.stream(requireNonNull(testFolder.listFiles()))
                    .forEach(File::delete);
        }
        testFolder.delete();
    }

    // =============================================================
    // Scenario: User specified file path to save candidates' data,
    //           but not schedule data
    // =============================================================
    @Test
    public void execute_onlyCandidateFileSpecifiedAndFileExistsAndFileShouldNotBeOverwritten_failure()
            throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.emptyPath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        FileUtil.createIfMissing(candidatesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, candidatesFilePath.toAbsolutePath()));
    }

    @Test
    public void execute_onlyCandidateFileSpecifiedAndFileDoesNotExistAndFileShouldNotBeOverwritten_success() {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.emptyPath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage = String.format(
                MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, candidatesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onlyCandidateFileSpecifiedAndFileCanBeOverwritten_success() throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.emptyPath;
        boolean shouldOverwriteFile = true;
        boolean shouldSaveAllData = false;

        // Scenario 1: File exists
        FileUtil.createIfMissing(candidatesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage = String.format(
                MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, candidatesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        // Scenario 2: File does not exist
        File file = candidatesFilePath.toFile();
        file.delete();

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    // =============================================================
    // Scenario: User specified file path to save schedule data,
    //           but not candidates' data
    // =============================================================
    @Test
    public void execute_onlyScheduleFileSpecifiedAndFileExistsAndFileShouldNotBeOverwritten_failure()
            throws IOException {
        Path candidatesFilePath = this.emptyPath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        FileUtil.createIfMissing(schedulesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, schedulesFilePath.toAbsolutePath()));
    }

    @Test
    public void execute_onlyScheduleFileSpecifiedAndFileDoesNotExistAndFileShouldNotBeOverwritten_success() {
        Path candidatesFilePath = this.emptyPath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage = String.format(
                MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, schedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_onlyScheduleFileSpecifiedAndFileCanBeOverwritten_success() throws IOException {
        Path candidatesFilePath = this.emptyPath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = true;
        boolean shouldSaveAllData = false;

        // Scenario 1: File exists
        FileUtil.createIfMissing(schedulesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage = String.format(
                MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, schedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        // Scenario 2: File does not exist
        File file = schedulesFilePath.toFile();
        file.delete();

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    // =============================================================
    // Scenario: User specified file paths to save schedule data,
    //           and candidates' data
    // =============================================================
    @Test
    public void execute_bothFilesSpecifiedAndEitherFileExistsAndFileShouldNotBeOverwritten_failure()
            throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        // Scenario 1: Candidate file exists, schedule file does not
        FileUtil.createIfMissing(candidatesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, candidatesFilePath.toAbsolutePath()));

        // Scenario 2: Schedule file exists, candidate file does not
        File file = candidatesFilePath.toFile();
        file.delete();
        FileUtil.createIfMissing(schedulesFilePath);

        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, schedulesFilePath.toAbsolutePath()));
    }

    @Test
    public void execute_bothFilesSpecifiedAndBothFilesExistAndFileShouldNotBeOverwritten_failure() throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = false;
        boolean shouldSaveAllData = false;

        FileUtil.createIfMissing(candidatesFilePath);
        FileUtil.createIfMissing(schedulesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);

        // Candidates file is checked first, hence error message will reflect accordingly
        assertCommandFailure(saveCommand, model,
                String.format(Messages.MESSAGE_FILE_EXISTS, candidatesFilePath.toAbsolutePath()));
    }

    @Test
    public void execute_bothFilesSpecifiedAndEitherFileExistsAndFileCanBeOverwritten_success() throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = true;
        boolean shouldSaveAllData = false;

        // Scenario 1: Candidate file exists, schedule file does not
        FileUtil.createIfMissing(candidatesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage =
                String.format(MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, candidatesFilePath.toAbsolutePath())
                        + System.lineSeparator()
                        + String.format(MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, schedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        // Scenario 2: Schedule file exists, candidate file does not
        File file = candidatesFilePath.toFile();
        file.delete();
        FileUtil.createIfMissing(schedulesFilePath);

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_bothFilesSpecifiedAndBothFilesExistsAndFileCanBeOverwritten_success() throws IOException {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldOverwriteFile = true;
        boolean shouldSaveAllData = false;

        FileUtil.createIfMissing(candidatesFilePath);
        FileUtil.createIfMissing(schedulesFilePath);

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage =
                String.format(MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, candidatesFilePath.toAbsolutePath())
                        + System.lineSeparator()
                        + String.format(MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, schedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_bothFilesSpecifiedAndBothFilesDoNotExist_success() {
        Path candidatesFilePath = this.validCandidatesFilePath;
        Path schedulesFilePath = this.validSchedulesFilePath;
        boolean shouldSaveAllData = false;

        // Scenario 1: File cannot be overwritten
        boolean shouldOverwriteFile = false;

        SaveCommand saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        String expectedMessage =
                String.format(MESSAGE_SAVE_CANDIDATES_FILE_SUCCESS, candidatesFilePath.toAbsolutePath())
                        + System.lineSeparator()
                        + String.format(MESSAGE_SAVE_SCHEDULES_FILE_SUCCESS, schedulesFilePath.toAbsolutePath());
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new ScheduleBoard());

        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);

        // Scenario 2: File can be overwritten
        shouldOverwriteFile = true;

        File file = candidatesFilePath.toFile();
        file.delete();
        file = schedulesFilePath.toFile();
        file.delete();

        saveCommand = new SaveCommand(candidatesFilePath, schedulesFilePath,
                shouldSaveAllData, shouldOverwriteFile);
        assertCommandSuccess(saveCommand, model, expectedMessage, expectedModel);
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
