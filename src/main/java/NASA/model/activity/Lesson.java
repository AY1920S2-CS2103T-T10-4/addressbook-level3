package nasa.model.activity;

/**
 * Represents Lesson method in Nasa book. New lesson re-instantiates itself after the current lesson's status is DONE.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson extends Activity {
    private int numOfDaysTillNextLesson = 7; // Frequency of lesson in number of days (eg. every 7 days) Default is 7 days.

    /**
     * Initialise Event with default "lesson".
     * Every field must be present and not null.
     */
    public Lesson(Date date, Note note) {
        super(new Name("lesson"), date, note);
    }

    /**
     * Initialise Deadlines with a particular unique {@code name}.
     * Every field must be present and not null.
     */
    public Lesson(Name name, Date date, Note note) {
        super(name, date, note);
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
        Lesson newLesson = new Lesson(this.getName(), this.getDate().addDaysToCurrDate(numOfDaysTillNextLesson),
                this.getNote());
        return newLesson;
    }

}
