package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;

/**
 * A class to access ScheduleBoard data stored as a json file on the hard disk.
 */
public class JsonScheduleBoardStorage implements ScheduleBoardStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonScheduleBoardStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getScheduleBoardFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard() throws DataLoadingException {
        return readScheduleBoard(filePath);
    }

    /**
     * Similar to {@link #readScheduleBoard()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableScheduleBoard> jsonScheduleBoard = JsonUtil.readJsonFile(
                filePath, JsonSerializableScheduleBoard.class);
        if (!jsonScheduleBoard.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonScheduleBoard.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard) throws IOException {
        saveScheduleBoard(scheduleBoard, filePath);
    }

    /**
     * Similar to {@link #saveScheduleBoard(ReadOnlyScheduleBoard)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard, Path filePath) throws IOException {
        requireNonNull(scheduleBoard);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableScheduleBoard(scheduleBoard), filePath);
    }

}

