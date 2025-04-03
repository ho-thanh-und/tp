package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.ReadOnlyScheduleBoard;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleBoard;

/**
 * An Immutable Schedule Board that is serializable to JSON format.
 */
@JsonRootName(value = "scheduleboard")
class JsonSerializableScheduleBoard {

    public static final String MESSAGE_CLASHING_SCHEDULE = "Schedule list contains clashing schedule(s).";

    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScheduleBoard} with the given schedules.
     */
    @JsonCreator
    public JsonSerializableScheduleBoard(@JsonProperty("schedules") List<JsonAdaptedSchedule> schedules) {
        this.schedules.addAll(schedules);
    }

    /**
     * Converts a given {@code ReadOnlyScheduleBoard} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableScheduleBoard}.
     */
    public JsonSerializableScheduleBoard(ReadOnlyScheduleBoard source) {
        schedules.addAll(source.getScheduleList().stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule board into the model's {@code ScheduleBoard} object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints violated.
     */
    public ScheduleBoard toModelType() throws IllegalValueException {
        ScheduleBoard scheduleBoard = new ScheduleBoard();
        for (JsonAdaptedSchedule jsonAdaptedSchedule : schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (scheduleBoard.hasSchedule(schedule) || scheduleBoard.hasSameDateTime(schedule)) {
                throw new IllegalValueException(MESSAGE_CLASHING_SCHEDULE);
            }
            scheduleBoard.addSchedule(schedule);
        }
        return scheduleBoard;
    }

}
