package seedu.address.model;

import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.person.JobRole;
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
     * Returns an unmodifiable view of the jobRole list.
     * This list will not contain any duplicate jobRole.
     */
    ObservableList<JobRole> getJobRoleList();

    Map<JobRole, Long> getJobApplicantStatistics();
}
