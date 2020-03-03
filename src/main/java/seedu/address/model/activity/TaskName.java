package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Activity's TaskName in NASA.
 * Guarantees: immutable; is valid as declared in {@link #isValidTask(String)}
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS = "Task can take any values, and it should not be blank";

    /*
     * The first character of the task must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\S+$";

    public final String code;

    /**
     * Constructs an {@code TaskName}.
     *
     * @param code A valid code name.
     */
    public TaskName(String code) {
        requireNonNull(code);
        this.code = code;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && code.equals(((TaskName) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
