package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "Name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "Phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "Email");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "Address");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "Tag");
    public static final Prefix PREFIX_LABEL = new Prefix("l/", "Label");
    public static final Prefix PREFIX_SCHEDULE = new Prefix("s/", "Schedule");
    public static final Prefix PREFIX_JOBROLE = new Prefix("j/", "Job role");
    public static final Prefix PREFIX_REMARK = new Prefix("r/", "Remark");
    public static final Prefix PREFIX_CANDIDATE = new Prefix("c/", "Candidate");
    public static final Prefix PREFIX_MODE = new Prefix("m/", "Mode");

    /* Prefix and suffix definitions - specific to save command */
    public static final Prefix PREFIX_CANDIDATES_FILE_PATH = new Prefix("c/", "CandidateDetailsFilePath");
    public static final Prefix PREFIX_SCHEDULES_FILE_PATH = new Prefix("s/", "InterviewSchedulesFilePath");
    public static final Prefix SUFFIX_SAVE_ALL = new Prefix("/a", "");
    public static final Prefix SUFFIX_OVERWRITE_FILE = new Prefix("/f", "");

}
