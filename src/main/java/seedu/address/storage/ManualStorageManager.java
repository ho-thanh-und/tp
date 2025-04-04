package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}
 * and {@link seedu.address.model.schedule.ScheduleBoard}
 */
public class ManualStorageManager implements ManualStorage {

    private static final Logger logger = LogsCenter.getLogger(ManualStorageManager.class);
    private final AddressBookStorage addressBookStorage;
    private final ScheduleBoardStorage scheduleBoardStorage;

    /**
     * Creates a {@code ManualStorageManager} with the given {@code AddressBookStorage} and {@code ScheduleBoardStorage}
     */
    public ManualStorageManager(AddressBookStorage addressBookStorage, ScheduleBoardStorage scheduleBoardStorage) {
        requireAllNonNull(addressBookStorage, scheduleBoardStorage);
        this.addressBookStorage = addressBookStorage;
        this.scheduleBoardStorage = scheduleBoardStorage;
    }

    @Override
    public Path getAddressBookFilePath() {
        return this.addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public boolean hasAddressBookFilePath() {
        return !this.getAddressBookFilePath().equals(ManualStorage.EMPTY_PATH);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return this.readAddressBook(this.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read candidate contact data from file: " + filePath);
        return this.addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        this.saveAddressBook(addressBook, this.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to candidate contact data file: " + filePath);
        this.addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public Path getScheduleBoardFilePath() {
        return this.scheduleBoardStorage.getScheduleBoardFilePath();
    }

    @Override
    public boolean hasScheduleBoardFilePath() {
        return !this.getScheduleBoardFilePath().equals(ManualStorage.EMPTY_PATH);
    }

    @Override
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard() throws DataLoadingException, IOException {
        return this.readScheduleBoard(this.getScheduleBoardFilePath());
    }

    @Override
    public Optional<ReadOnlyScheduleBoard> readScheduleBoard(Path filePath) throws DataLoadingException, IOException {
        logger.fine("Attempting to read interview schedule data from file: " + filePath);
        return scheduleBoardStorage.readScheduleBoard(filePath);
    }

    @Override
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard) throws IOException {
        this.saveScheduleBoard(scheduleBoard, this.getScheduleBoardFilePath());
    }

    @Override
    public void saveScheduleBoard(ReadOnlyScheduleBoard scheduleBoard, Path filePath) throws IOException {
        logger.fine("Attempting to write interview schedule to data file: " + filePath);
        this.scheduleBoardStorage.saveScheduleBoard(scheduleBoard, filePath);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ManualStorageManager otherManualStorageManager)) {
            return false;
        }

        boolean sameAddressBookFilePath = this.getAddressBookFilePath().toAbsolutePath()
                .equals(otherManualStorageManager.getAddressBookFilePath().toAbsolutePath());
        boolean sameScheduleBoardFilePath = this.getScheduleBoardFilePath().toAbsolutePath()
                .equals(otherManualStorageManager.getScheduleBoardFilePath().toAbsolutePath());

        return sameAddressBookFilePath && sameScheduleBoardFilePath;
    }
}
