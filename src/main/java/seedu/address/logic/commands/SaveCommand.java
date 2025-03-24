package seedu.address.logic.commands;

import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_FORMAT;
import static seedu.address.logic.LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_FILE_EXISTS;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the address book to a file
 */
public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves all candidates' data in the application into "
            + "the file at the path provided. "
            + "Existing files **will not** be overwritten.\n"
            + "Parameters: FILE_PATH (must be a valid file path)\n"
            + "Example: " + COMMAND_WORD + " candidates_archive.json";
    public static final String MESSAGE_SAVE_SUCCESS = "Saved file at: '%1$s'";

    private final AddressBookStorage storage;
    private final boolean saveOnlyFiltered;

    public SaveCommand(Path filePath) {
        this(filePath, false);
    }

    /**
     * Constructor for {@code SaveCommand}
     *
     * @param filePath Path to file to save data to
     * @param saveOnlyFiltered Whether to save only filtered data (via {@code find}) - defaults to {@code false}
     */
    public SaveCommand(Path filePath, boolean saveOnlyFiltered) {
        this.storage = new JsonAddressBookStorage(filePath.toAbsolutePath());
        this.saveOnlyFiltered = saveOnlyFiltered;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path filePath = this.storage.getAddressBookFilePath();
        if (filePath.toFile().exists()) {
            throw new CommandException(generateFailureMessage(filePath));
        }

        try {
            AddressBook addressBookToSave = new AddressBook(model.getAddressBook());
            if (saveOnlyFiltered) {
                addressBookToSave.setPersons(model.getFilteredPersonList());
            }
            this.storage.saveAddressBook(addressBookToSave);
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return new CommandResult(generateSuccessMessage(filePath));
    }

    private String getFilePathAsString(Path filePath) {
        return filePath.toAbsolutePath().toString();
    }

    private String generateSuccessMessage(Path filePath) {
        return String.format(MESSAGE_SAVE_SUCCESS, getFilePathAsString(filePath));
    }

    private String generateFailureMessage(Path filePath) {
        return String.format(MESSAGE_FILE_EXISTS, getFilePathAsString(filePath));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SaveCommand saveCommand)) {
            return false;
        }

        return this.storage.getAddressBookFilePath().equals(saveCommand.storage.getAddressBookFilePath());
    }
}
