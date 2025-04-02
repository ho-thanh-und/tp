package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateJobTitleException;
import seedu.address.model.person.exceptions.JobTitleNotFoundException;

/**
 * A list of job titles that enforces uniqueness between its elements and does not allow nulls.
 * A job title is considered unique by comparing using {@code JobTitle#isSame(JobTitle)}.
 * Supports a minimal set of list operations.
 */
public class UniqueJobTitleList implements Iterable<JobTitle> {

    private final ObservableList<JobTitle> internalList = FXCollections.observableArrayList();
    private final ObservableList<JobTitle> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Default constructor for this list. The following jobTitles are by default added into this list.
     */
    public UniqueJobTitleList() {
        internalList.add(new JobTitle("Software Engineer"));
        internalList.add(new JobTitle("Data Scientist"));
        internalList.add(new JobTitle("Frontend Developer"));
        internalList.add(new JobTitle("Backend Developer"));
        internalList.add(new JobTitle("IT Administrator"));
        internalList.add(new JobTitle("UI Designer"));
        internalList.add(new JobTitle("Product Manager"));
        internalList.add(new JobTitle("DevOps Engineer"));
        internalList.add(new JobTitle("QA Engineer"));
    }

    /**
     * Returns true if the list contains an equivalent job title as the given argument.
     */
    public boolean contains(JobTitle toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a job title to the list.
     * The job title must not already exist in the list.
     */
    public void add(JobTitle toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateJobTitleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent job title from the list.
     * The job title must exist in the list.
     */
    public void remove(JobTitle toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new JobTitleNotFoundException();
        }
    }

    public void setJobTitles(UniqueJobTitleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code jobTitles}.
     * {@code jobTitles} must not contain duplicate jobTitles.
     */
    public void setJobTitles(List<JobTitle> jobTitles) {
        requireAllNonNull(jobTitles);
        if (!jobTitlesAreUnique(jobTitles)) {
            throw new DuplicateJobTitleException();
        }

        internalList.setAll(jobTitles);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<JobTitle> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<JobTitle> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UniqueJobTitleList)) {
            return false;
        }
        UniqueJobTitleList otherList = (UniqueJobTitleList) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code jobTitles} contains only unique job titles.
     */
    private boolean jobTitlesAreUnique(List<JobTitle> jobTitles) {
        for (int i = 0; i < jobTitles.size() - 1; i++) {
            for (int j = i + 1; j < jobTitles.size(); j++) {
                if (jobTitles.get(i).equals(jobTitles.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
