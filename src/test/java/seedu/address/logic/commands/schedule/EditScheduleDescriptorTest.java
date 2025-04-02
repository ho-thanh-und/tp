package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditScheduleDescriptor descriptorWithSameValues = new EditScheduleDescriptor(DESC_SCHEDULE_1);
        assertTrue(DESC_SCHEDULE_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SCHEDULE_1.equals(DESC_SCHEDULE_1));

        // null -> returns false
        assertFalse(DESC_SCHEDULE_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_SCHEDULE_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_SCHEDULE_1.equals(DESC_SCHEDULE_2));

        // different date -> returns false
        EditScheduleDescriptor editedFirstSchedule = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_1)
                .withDate(VALID_DATE_2).withStartTime(VALID_START_TIME).withEndTime(VALID_END_TIME).build();
        assertFalse(DESC_SCHEDULE_1.equals(editedFirstSchedule));

        // different mode -> returns false
        editedFirstSchedule = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_1).withMode(VALID_MODE_2).build();
        assertFalse(DESC_SCHEDULE_1.equals(editedFirstSchedule));

    }

    @Test
    public void toStringMethod() {
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        String expected = EditScheduleDescriptor.class.getCanonicalName() + "{date="
                + editScheduleDescriptor.getDate().orElse(null) + ", startTime="
                + editScheduleDescriptor.getStartTime().orElse(null) + ", endTime="
                + editScheduleDescriptor.getEndTime().orElse(null) + ", mode="
                + editScheduleDescriptor.getMode().orElse(null) + "}";
        assertEquals(expected, editScheduleDescriptor.toString());
    }
}
