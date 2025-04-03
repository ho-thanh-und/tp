package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;

/**
 * An UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String STYLE_LABEL = "cell_small_label";

    private static final String FXML = "ScheduleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Schedule schedule;

    @FXML
    private HBox scheduleCardPane;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label mode;
    @FXML
    private Label candidateName;
    @FXML
    private Label candidateEmail;

    /**
     * Creates a {@code ScheduleCode} with the given {@code Schedule} and index to display.
     */
    public ScheduleCard(Schedule schedule, int displayedIndex) {
        super(FXML);
        this.schedule = schedule;
        id.setText(displayedIndex + ". ");
        date.setText(schedule.getDate().toString());
        startTime.setText(schedule.getStartTime().toString() + " - ");
        endTime.setText(schedule.getEndTime().toString());
        mode.setText(schedule.getMode().toString());
        candidateName.setText(schedule.getCandidateName().toString());
        candidateEmail.setText(schedule.getCandidateEmail().toString());

        if (schedule.isPast()) {
            scheduleCardPane.getStyleClass().add("past-schedule");
        }
    }
}

