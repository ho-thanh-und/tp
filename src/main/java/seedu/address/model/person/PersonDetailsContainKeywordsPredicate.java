package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s details (i.e., any of its attributes) matches any of the keywords given.
 */
public class PersonDetailsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonDetailsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> personDetailsContainKeyword(person, keyword));
    }

    private boolean personDetailsContainKeyword(Person person, String keyword) {
        return StringUtil.containsPartialWordIgnoreCase(person.getName().fullName, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getPhone().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getAddress().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getEmail().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getJobTitle().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getSchedule().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getLabel().value, keyword)
                || StringUtil.containsPartialWordIgnoreCase(person.getRemark().value, keyword)
                || person.getTags().stream()
                    .anyMatch(tag -> StringUtil.containsPartialWordIgnoreCase(tag.tagName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailsContainKeywordsPredicate)) {
            return false;
        }

        PersonDetailsContainKeywordsPredicate otherPersonDetailsContainKeywordsPredicate =
                (PersonDetailsContainKeywordsPredicate) other;
        return keywords.equals(otherPersonDetailsContainKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
