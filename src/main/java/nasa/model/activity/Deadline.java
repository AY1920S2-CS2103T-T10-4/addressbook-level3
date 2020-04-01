package nasa.model.activity;

import static java.util.Objects.requireNonNull;
import static nasa.commons.util.AppUtil.checkArgument;
import static nasa.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents Deadlines method in NASA.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline extends Activity {

    public static final String DUE_DATE_CONSTRAINTS =
            "Deadline should be after date of creation.";

    private Date dueDate;

    public Deadline(Name name, Date dueDate) {
        super(name);
        requireNonNull(dueDate);
        checkArgument(isExpiredDueDate(dueDate), DUE_DATE_CONSTRAINTS);
        this.dueDate = dueDate;
    }

    public Deadline(Name name, Date dueDate, Note note) {
        super(name, note);
        requireNonNull(dueDate);
        checkArgument(isExpiredDueDate(dueDate), DUE_DATE_CONSTRAINTS);
        this.dueDate = dueDate;
    }

    public Deadline(Name name, Note note, Priority priority, Date dueDate) {
        super(name, note, priority);
        requireAllNonNull(name, dueDate);
        this.dueDate = dueDate;
    }

    /**
     * Initialise Deadlines with a particular unique {@code name}.
     * Every field must be present and not null.
     * @param name Name
     * @param date Date
     * @param note Note
     * @param status Status
     * @param priority Priority
     * @param dueDate Date
     */
    public Deadline(Name name, Date date, Note note, Status status, Priority priority, Date dueDate) {
        super(name, date, note, status, priority);
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
        this.updateStatus();
    }

    /**
     * Return the difference in due date and date of creation.
     * @return int
     */
    public int getDifferenceInDay() {
        return (int) getDate().getDifference(dueDate)[0];
    }

    /**
     * Calculate the percentage towards the dateline.
     * @return int
     */
    public int percentage() {
        double currentDifference = (double) Date.now().getDifference(getDate())[0] * -1;
        return (int) (100 * (currentDifference / getDifferenceInDay()));
    }

    /**
     * Get days remaining for the task.
     * @return int
     */
    public int getDaysRemaining() {
        return (int) this.dueDate.getDifference(Date.now())[0];
    }

    @Override
    public void updateStatus() {
        if (status == Status.ONGOING && Date.now().isAfter(getDueDate())) {
            status = Status.LATE;
        }
    }

    public static boolean isExpiredDueDate(Date date) {
        return date.isAfter(Date.now());
    }

    @Override
    public Deadline regenerate() {
        if (super.getSchedule().update()) {
            setDueDate(getSchedule().getDate().addDaysToCurrDate(getDifferenceInDay()));
            setStatus(Status.ONGOING);
        }
        return this;
    }

    @Override
    public boolean occurInMonth(int month) {
        int dueDateMonth = dueDate.getDate().getMonth().getValue();
        return month == dueDateMonth;
    }
}
