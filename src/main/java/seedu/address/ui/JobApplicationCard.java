package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class JobApplicationCard extends UiPart<Region> {

    private static final String MESSAGE_SCHEDULE = "Interview Date and Time: %s";
    private static final String MESSAGE_REMARK = "Remark: %s";

    private static final String STYLE_LABEL = "cell_small_label";

    private static final String FXML = "JobApplicationCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox fullCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label jobTitle;
    @FXML
    private FlowPane schedule;
    @FXML
    private FlowPane remark;
    @FXML
    private FlowPane tags;
    @FXML
    private Label label;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public JobApplicationCard(Person person) {
        super(FXML);
        this.person = person;
        if (person == null) {
            name.setText("");
            phone.setText("");
            address.setText("");
            email.setText("");
            label.setText("");
            jobTitle.setText("");
            schedule.getChildren().clear();
            remark.getChildren().clear();
            tags.getChildren().clear();
            return;
        }
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        String labelValue = person.getLabel().value;
        if (!labelValue.isEmpty()) {
            label.setText(person.getLabel().value);
        } else {
            label.setText("N/A");
        }

        String jobTitleValue = person.getJobTitle().value;
        if (!jobTitleValue.isEmpty()) {
            jobTitle.setText(person.getJobTitle().value);
        } else {
            jobTitle.setText("N/A");
        }

        String scheduleValue = person.getSchedule().value;
        if (!scheduleValue.isEmpty()) {
            Label scheduleLabel = createLabel(String.format(MESSAGE_SCHEDULE, scheduleValue));
            schedule.getChildren().addAll(scheduleLabel);
        }

        String remarkValue = person.getRemark().value;
        if (!remarkValue.isEmpty()) {
            Label remarkLabel = createLabel(String.format(MESSAGE_REMARK, remarkValue));
            remark.getChildren().addAll(remarkLabel);
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private Label createLabel(String text) {
        Label uiLabel = new Label(text);
        uiLabel.getStyleClass().addAll(JobApplicationCard.STYLE_LABEL);
        return uiLabel;
    }

    public void show() {
        getRoot().setVisible(true);
        getRoot().setManaged(true);
    }

    public void hide() {
        getRoot().setVisible(false);
        getRoot().setManaged(false);
    }

    // Add the new clear method to empty the card's contents.
    public void clear() {
        // Clear all text fields.
        name.setText("");
        phone.setText("");
        address.setText("");
        email.setText("");
        label.setText("");
        jobTitle.setText("");
        // Clear container nodes.
        schedule.getChildren().clear();
        remark.getChildren().clear();
        tags.getChildren().clear();
    }
}
