package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.person.Mode;
import seedu.address.model.schedule.Schedule;

/**
 * A utility class to help with building EditScheduleDescriptor objects.
 */
public class EditScheduleDescriptorBuilder {

    private EditScheduleDescriptor descriptor;

    public EditScheduleDescriptorBuilder() {
        descriptor = new EditScheduleDescriptor();
    }

    public EditScheduleDescriptorBuilder(EditScheduleDescriptor descriptor) {
        this.descriptor = new EditScheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditScheduleDescriptor} with fields containing {@code schedule}'s details
     */
    public EditScheduleDescriptorBuilder(Schedule schedule) {
        descriptor = new EditScheduleDescriptor();
        descriptor.setDate(schedule.getDate());
        descriptor.setStartTime(schedule.getStartTime());
        descriptor.setEndTime(schedule.getEndTime());
        descriptor.setMode(schedule.getMode());
    }

    /**
     * Sets the {@code date} of the {@code Schedule} that we are building.
     */
    public EditScheduleDescriptorBuilder withDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        descriptor.setDate(LocalDate.parse(date, formatter));
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Schedule} that we are building.
     */
    public EditScheduleDescriptorBuilder withStartTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        descriptor.setStartTime(LocalTime.parse(startTime, formatter));
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Schedule} that we are building.
     */
    public EditScheduleDescriptorBuilder withEndTime(String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        descriptor.setEndTime(LocalTime.parse(endTime, formatter));
        return this;
    }

    /**
     * Sets the {@code Mode} of the {@code Schedule} that we are building.
     */
    public EditScheduleDescriptorBuilder withMode(String mode) {
        descriptor.setMode(mode.equalsIgnoreCase("online") ? Mode.ONLINE : Mode.OFFLINE);
        return this;
    }



    /**
     * Builds the {@code EditScheduleDescriptor} and returns it
     */
    public EditScheduleDescriptor build() {
        return descriptor;
    }
}
