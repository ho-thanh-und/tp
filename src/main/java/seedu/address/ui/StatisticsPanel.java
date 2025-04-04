package seedu.address.ui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @param dynamicJobRoles A dynamic list of job roles (retrieved from AddressBook).
     */
    public void setStatistics(Map<JobRole, Long> stats, List<JobRole> dynamicJobRoles) {
        // Convert dynamicJobRoles to a sorted list of raw values
        List<String> allJobRoles = dynamicJobRoles.stream()
                .map(JobRole::getValue)
                .sorted()
                .collect(Collectors.toList());

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        for (String role : allJobRoles) {
            // Create a new JobRole from the raw value for lookup in the stats map.
            // (This works because equals() is based on the underlying value.)
            Long count = stats.getOrDefault(new JobRole(role), 0L);
            data.add(new XYChart.Data<>(role, count));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setData(data);
        statsBarChart.getData().clear();
        statsBarChart.getData().add(series);
    }

    /**
     * Convenience method to populate the bar chart when no dynamic list is provided.
     *
     * @param stats A map of job roles to applicant counts.
     */
    public void setStatistics(Map<JobRole, Long> stats) {
        setStatistics(stats, FXCollections.observableArrayList());
    }
}
