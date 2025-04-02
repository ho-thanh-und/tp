package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.ScheduleBoard;

public class JsonScheduleBoardStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonScheduleBoardStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readScheduleBoard_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readScheduleBoard(null));
    }

    private java.util.Optional<ReadOnlyScheduleBoard> readScheduleBoard(String filePath) throws Exception {
        return new JsonScheduleBoardStorage(Paths.get(filePath))
                .readScheduleBoard(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readScheduleBoard("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readScheduleBoard("notJsonFormatScheduleBoard.json"));
    }

    @Test
    public void readScheduleBoard_invalidScheduleScheduleBoard_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readScheduleBoard("invalidScheduleScheduleBoard.json"));
    }

    @Test
    public void readScheduleBoard_invalidAndValidScheduleScheduleBoard_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readScheduleBoard("invalidAndValidScheduleScheduleBoard.json"));
    }

    @Test
    public void saveScheduleBoard_nullScheduleBoard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleBoard(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ScheduleBoard} at the specified {@code filePath}.
     */
    private void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard, String filePath) {
        try {
            new JsonScheduleBoardStorage(Paths.get(filePath))
                    .saveScheduleBoard(scheduleBoard, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveScheduleBoard_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleBoard(new ScheduleBoard(), null));
    }
}
