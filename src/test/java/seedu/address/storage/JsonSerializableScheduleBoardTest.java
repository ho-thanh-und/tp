package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.schedule.ScheduleBoard;
import seedu.address.testutil.TypicalSchedules;

public class JsonSerializableScheduleBoardTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableScheduleBoardTest");
    private static final Path TYPICAL_SCHEDULES_FILE = TEST_DATA_FOLDER.resolve("typicalSchedulesScheduleBoard.json");
    private static final Path INVALID_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("invalidScheduleScheduleBoard.json");
    private static final Path DUPLICATE_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("duplicateScheduleScheduleBoard.json");

    @Test
    public void toModelType_typicalSchedulesFile_success() throws Exception {
        JsonSerializableScheduleBoard dataFromFile = JsonUtil.readJsonFile(TYPICAL_SCHEDULES_FILE,
                JsonSerializableScheduleBoard.class).get();
        ScheduleBoard scheduleBoardFromFile = dataFromFile.toModelType();
        ScheduleBoard typicalSchedulesScheduleBoard = TypicalSchedules.getTypicalScheduleBoard();
        assertEquals(scheduleBoardFromFile, typicalSchedulesScheduleBoard);
    }

    @Test
    public void toModelType_invalidScheduleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduleBoard dataFromFile = JsonUtil.readJsonFile(INVALID_SCHEDULE_FILE,
                JsonSerializableScheduleBoard.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSchedules_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduleBoard dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SCHEDULE_FILE,
                JsonSerializableScheduleBoard.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableScheduleBoard.MESSAGE_DUPLICATE_SCHEDULE,
                dataFromFile::toModelType);
    }

}
