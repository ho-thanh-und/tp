package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonDetailsContainKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonDetailsContainKeywordsPredicate firstPredicate =
                new PersonDetailsContainKeywordsPredicate(firstPredicateKeywordList);
        PersonDetailsContainKeywordsPredicate secondPredicate =
                new PersonDetailsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonDetailsContainKeywordsPredicate firstPredicateCopy =
                new PersonDetailsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personDetailsContainKeywords_returnsTrue() {
        // One keyword
        PersonDetailsContainKeywordsPredicate predicate =
                new PersonDetailsContainKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Collections.singletonList("98764321"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withPhone("98764321").build()));

        // Multiple keywords
        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("UI", "Designer"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withJobTitle("UI Designer").build()));

        // Only one matching keyword
        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("Codes", "Java"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withRemark("Codes in Python").build()));

        // Mixed-case keywords
        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("uI", "dEsigner"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withJobTitle("UI Designer").build()));

        // Keywords match some fields
        predicate = new PersonDetailsContainKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Partial-matching keyword
        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("ali"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("234"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withPhone("12345").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("bob@example.com"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withEmail("alicebob@example.com").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("Str"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withAddress("Main Street").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("2025"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("design"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withJobTitle("UI Designer").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("accept"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withLabel("Accepted").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("code"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withRemark("Codes in Java").build()));

        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friends").build()));
    }

    @Test
    public void test_personDetailsContainKeywords_returnsFalse() {
        // Zero keywords
        PersonDetailsContainKeywordsPredicate predicate =
                new PersonDetailsContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonDetailsContainKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonDetailsContainKeywordsPredicate predicate = new PersonDetailsContainKeywordsPredicate(keywords);

        String expected =
                PersonDetailsContainKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
