package seedu.address.ui;

import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.model.person.JobRole;

/**
 * A new window that displays job statistics in a bar chart.
 */
public class StatisticsWindow {

    private static final String TITLE = "Job Statistics";
    private Stage stage;

    /**
     * Constructs a new StatisticsWindow.
     * This creates a new Stage and sets the title to "Job Statistics".
     */
    public StatisticsWindow() {
        stage = new Stage();
        stage.setTitle(TITLE);
    }

    /**
     * Sets the statistics to be displayed in the window.
     * @param stats A map of job roles to applicant counts.
     */
    public void setStatistics(Map<JobRole, Long> stats, List<JobRole> dynamicJobRoles) {
        StatisticsPanel statsPanel = new StatisticsPanel();
        statsPanel.setStatistics(stats, dynamicJobRoles);
        Scene scene = new Scene(statsPanel.getRoot());
        stage.setScene(scene);
    }

    /**
     * Displays the statistics window.
     */
    public void show() {
        stage.show();
    }
}
