package seedu.address.model;

import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the jobTitle list.
     * This list will not contain any duplicate jobTitle.
     */
    ObservableList<JobTitle> getJobTitleList();

    Map<JobTitle, Long> getJobApplicantStatistics();
}
