package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2425s2-cs2103t-t16-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USER_GUIDE_URL;

    private static final String THEME_LIGHT = "Light";
    private static final String THEME_DARK = "Dark";
    private static final String STYLESHEET_FORMAT = "view/%1$sHelpWindow.css";
    public static final String STYLESHEET_THEME_LIGHT = String.format(STYLESHEET_FORMAT, THEME_LIGHT);
    public static final String STYLESHEET_THEME_DARK = String.format(STYLESHEET_FORMAT, THEME_DARK);

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private Stage stage;

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        this.stage = root;
        this.helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

    private void setStylesheet(String stylesheetPath) {
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add(stylesheetPath);
    }

    /**
     * Changes the colorscheme for help window to match a light theme
     */
    public void setLightTheme() {
        this.setStylesheet(STYLESHEET_THEME_LIGHT);
    }

    /**
     * Changes the colorscheme for help window to match a dark theme
     */
    public void setDarkTheme() {
        this.setStylesheet(STYLESHEET_THEME_DARK);
    }
}

