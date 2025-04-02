package seedu.address.ui;

import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.model.person.JobTitle;

/**
 * A new window that displays job statistics in a bar chart.
 */
public class StatisticsWindow {

    private Stage stage;
    private static final String TITLE = "Job Statistics";

    public StatisticsWindow() {
        stage = new Stage();
        stage.setTitle(TITLE);
    }

    /**
     * Sets the statistics to be displayed in the window.
     * @param stats A map of job titles to applicant counts.
     */
    public void setStatistics(Map<JobTitle, Long> stats) {
        StatisticsPanel statsPanel = new StatisticsPanel();
        statsPanel.setStatistics(stats);
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
