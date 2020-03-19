package nasa.model.activity;

import static nasa.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents Lesson method in Nasa book. New lesson re-instantiates itself after the current lesson's status is DONE.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson extends Activity {
    private int numOfDaysTillNextLesson = 7; // Frequency of lesson in number of days (eg. every 7 days) Default is 7.
    private Date startDate;
    private Date endDate;

    /**
     * Initialise Lessons with default status and priority.
     */
    public Lesson(Name name, Date startDate, Date endDate) {
        super(name);
        requireAllNonNull(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Lesson(Name name, Note note, Date startDate, Date endDate) {
        super(name, note);
        requireAllNonNull(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Lesson(Name name, Note note, Priority priority, Date startDate, Date endDate) {
        super(name, note, priority);
        requireAllNonNull(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Initialise Deadlines with a particular unique {@code name}.
     * Every field must be present and not null.
     */
    public Lesson(Name name, Date date, Note note, Status status, Priority priority, Date startDate, Date endDate) {
        super(name, date, note, status, priority);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getDateFrom() {
        return startDate;
    }

    public Date getDateTo() {
        return endDate;
    }

    public int getNumOfDaysTillNextLesson() {
        return numOfDaysTillNextLesson;
    }

    public void setNumOfDaysTillNextLesson(int newNumOfDays) {
        this.numOfDaysTillNextLesson = newNumOfDays;
    }

    /**
     * Initialise new lesson with the new date.
     * @return new instance of lesson at the new date
     */
    public Lesson regenerate() {
        //Lesson newLesson = new Lesson(this.getName(), this.getDate().addDaysToCurrDate(numOfDaysTillNextLesson),
                //this.getNote());
        //return newLesson;
        return null;
    }
}
