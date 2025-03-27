package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;

/**
 * Represents a storage for {@link seedu.address.model.schedule.Schedule}.
 */
public interface ScheduleBoardStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getScheduleBoardFilePath();

    /**
     * Returns Schedule data as a {@link ReadOnlyScheduleBoard}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataLoadingException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyScheduleBoard> readScheduleBoard() throws DataLoadingException, IOException;

    /**
     * @see #getScheduleBoardFilePath()
     */
    Optional<ReadOnlyScheduleBoard> readScheduleBoard(Path filePath) throws DataLoadingException, IOException;

    /**
     * Saves the given {@link ReadOnlyScheduleBoard} to the storage.
     * @param schedules cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduleBoard(ReadOnlyScheduleBoard schedules) throws IOException;

    /**
     * @see #saveScheduleBoard(seedu.address.model.schedule.ReadOnlyScheduleBoard)
     */
    void saveScheduleBoard(ReadOnlyScheduleBoard schedules, Path filePath) throws IOException;
}
