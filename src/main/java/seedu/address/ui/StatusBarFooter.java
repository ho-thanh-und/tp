package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label saveScheduleBoardLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveCandidateListLocation, Path saveScheduleBoardLocation) {
        super(FXML);
        saveLocationStatus.setText("Candidate list: " + Paths.get(".").resolve(saveCandidateListLocation).toString());
        saveScheduleBoardLocationStatus.setText("Schedule board: "
                + Paths.get(".").resolve(saveScheduleBoardLocation)
                .toString());
    }

}
