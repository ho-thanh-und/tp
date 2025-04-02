package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobTitles.JOB_TITLE_IN_DEFAULT_LIST;
import static seedu.address.testutil.TypicalJobTitles.JOB_TITLE_NOT_IN_DEFAULT_LIST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.exceptions.DuplicateJobTitleException;
import seedu.address.model.person.exceptions.JobTitleNotFoundException;

public class UniqueJobTitleListTest {

    private UniqueJobTitleList uniqueJobTitleList = new UniqueJobTitleList();

    @Test
    public void contains_nullJobTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobTitleList.contains(null));
    }

    @Test
    public void contains_jobTitleNotInList_returnsFalse() {
        assertFalse(uniqueJobTitleList.contains(JOB_TITLE_NOT_IN_DEFAULT_LIST));
    }

    @Test
    public void contains_jobTitleInList_returnsTrue() {
        uniqueJobTitleList.add(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        assertTrue(uniqueJobTitleList.contains(JOB_TITLE_NOT_IN_DEFAULT_LIST));
    }

    @Test
    public void contains_defaultJobTitleInList_returnsTrue() {
        assertTrue(uniqueJobTitleList.contains(JOB_TITLE_IN_DEFAULT_LIST));
    }

    @Test
    public void add_nullJobTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobTitleList.add(null));
    }

    @Test
    public void add_duplicateJobTitle_throwsDuplicateJobTitleException() {
        assertThrows(DuplicateJobTitleException.class, () -> uniqueJobTitleList.add(JOB_TITLE_IN_DEFAULT_LIST));
    }

    @Test
    public void remove_nullJobTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobTitleList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsJobTitleNotFoundException() {
        assertThrows(JobTitleNotFoundException.class, () -> uniqueJobTitleList.remove(JOB_TITLE_NOT_IN_DEFAULT_LIST));
    }

    @Test
    public void remove_existingJobTitle_removesJobTitle() {
        uniqueJobTitleList.add(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        uniqueJobTitleList.remove(JOB_TITLE_NOT_IN_DEFAULT_LIST);
        UniqueJobTitleList expectedUniqueJobTitleList = new UniqueJobTitleList();
        assertEquals(expectedUniqueJobTitleList, uniqueJobTitleList);
    }

    @Test
    public void setJobTitles_nullUniqueJobTitleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobTitleList.setJobTitles((UniqueJobTitleList) null));
    }

    @Test
    public void setJobTitles_list_replacesOwnListWithProvidedUniqueJobTitleList() {
        UniqueJobTitleList expectedUniqueJobTitleList = new UniqueJobTitleList();
        expectedUniqueJobTitleList.remove(JOB_TITLE_IN_DEFAULT_LIST);
        uniqueJobTitleList.setJobTitles(expectedUniqueJobTitleList);
        assertEquals(expectedUniqueJobTitleList, uniqueJobTitleList);
    }

    @Test
    public void setJobTitles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobTitleList.setJobTitles((List<JobTitle>) null));
    }

    @Test
    public void setJobTitles_list_replacesOwnListWithProvidedList() {
        List<JobTitle> jobTitleList = Collections.singletonList(JOB_TITLE_IN_DEFAULT_LIST);
        uniqueJobTitleList.setJobTitles(jobTitleList);
        UniqueJobTitleList expectedUniqueJobTitleList = new UniqueJobTitleList();
        expectedUniqueJobTitleList.setJobTitles(Collections.emptyList());
        expectedUniqueJobTitleList.add(JOB_TITLE_IN_DEFAULT_LIST);
        assertEquals(expectedUniqueJobTitleList, uniqueJobTitleList);
    }

    @Test
    public void setJobTitles_listWithDuplicateJobTitles_throwsDuplicateJobTitleException() {
        List<JobTitle> listWithDuplicateJobTitles = Arrays.asList(JOB_TITLE_IN_DEFAULT_LIST, JOB_TITLE_IN_DEFAULT_LIST);
        assertThrows(DuplicateJobTitleException.class, () -> uniqueJobTitleList
                .setJobTitles(listWithDuplicateJobTitles));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueJobTitleList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        UniqueJobTitleList uniqueJobTitleList2 = new UniqueJobTitleList();
        uniqueJobTitleList2.add(JOB_TITLE_NOT_IN_DEFAULT_LIST);

        // same object -> returns true
        assertTrue(uniqueJobTitleList.equals(uniqueJobTitleList));

        // same values -> returns true
        UniqueJobTitleList uniqueJobTitleListCopy = new UniqueJobTitleList();
        assertTrue(uniqueJobTitleList.equals(uniqueJobTitleListCopy));

        // different types -> returns false
        assertFalse(uniqueJobTitleList.equals(1));

        // null -> returns false
        assertFalse(uniqueJobTitleList.equals(null));

        // different person -> returns false
        assertFalse(uniqueJobTitleList.equals(uniqueJobTitleList2));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueJobTitleList.asUnmodifiableObservableList().toString(),
                uniqueJobTitleList.toString());
    }
}
