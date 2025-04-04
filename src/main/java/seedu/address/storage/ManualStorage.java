package seedu.address.storage;

import java.nio.file.Path;

/**
 * API to manage the manual Storage component
 * (when data storing is explicitly requested by the user)
 */
public interface ManualStorage extends AddressBookStorage, ScheduleBoardStorage {
    public static final Path EMPTY_PATH = Path.of("");

    /**
     * Returns {@code true} if address book file path
     * in {@code ManualStorage} is not empty, and {@code false} otherwise
     */
    boolean hasAddressBookFilePath();

    /**
     * Returns {@code true} if schedules file path
     * in {@code ManualStorage} is not empty, and {@code false} otherwise
     */
    boolean hasScheduleBoardFilePath();
}
