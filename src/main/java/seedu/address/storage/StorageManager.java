package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ScheduleBoardStorage scheduleBoardStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
            ScheduleBoardStorage scheduleBoardStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.scheduleBoardStorage = scheduleBoardStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ ScheduleBoard methods ==============================

    @Override
    public Path getScheduleBoardFilePath() {
        return scheduleBoardStorage.getScheduleBoardFilePath();
    }

    @Override
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard() throws DataLoadingException, IOException {
        return readScheduleBoard(scheduleBoardStorage.getScheduleBoardFilePath());
    }

    @Override
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard(Path filePath) throws DataLoadingException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleBoardStorage.readScheduleBoard(filePath);
    }

    @Override
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard) throws IOException {
        saveScheduleBoard(scheduleBoard, scheduleBoardStorage.getScheduleBoardFilePath());
    }

    @Override
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleBoardStorage.saveScheduleBoard(scheduleBoard, filePath);
    }
}
