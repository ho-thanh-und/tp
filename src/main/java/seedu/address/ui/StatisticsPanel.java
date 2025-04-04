package seedu.address.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.person.JobRole;

/**
 * Panel containing a bar chart that displays job applicant statistics.
 */
public class StatisticsPanel extends UiPart<Region> {
    private static final String FXML = "StatisticsPanel.fxml";

    @FXML
    private BarChart<String, Number> statsBarChart;

    @FXML
    private CategoryAxis jobRoleAxis;

    @FXML
    private NumberAxis countAxis;

    public StatisticsPanel() {
        super(FXML);
    }

    /**
     * Populates the bar chart with job statistics.
     * @param stats A map of job roles to applicant counts.
     *              Jobs with zero applicants will be displayed with a value of 0.
     */
    public void setStatistics(Map<JobRole, Long> stats) {
        List<String> allJobRoles = Arrays.asList(
                "Software Engineer",
                "Data Engineer",
                "Front End Developer",
                "IT Administrator",
                "Back End Developer",
                "UI Designer",
                "Product Manager",
                "Data Scientist",
                "DevOps engineer",
                "QA Engineer"
        );
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        for (String job : allJobRoles) {
            Long count = stats.getOrDefault(new JobRole(job), 0L);
            data.add(new XYChart.Data<>(job, count));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setData(data);
        statsBarChart.getData().clear();
        statsBarChart.getData().add(series);
    }
}
