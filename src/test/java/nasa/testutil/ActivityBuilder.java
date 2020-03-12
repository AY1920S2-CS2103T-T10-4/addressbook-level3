package nasa.testutil;

import nasa.model.activity.Activity;
import nasa.model.activity.Date;
import nasa.model.activity.Deadline;
import nasa.model.activity.Event;
import nasa.model.activity.Lesson;
import nasa.model.activity.Name;
import nasa.model.activity.Note;

/**
 * A utility class to help with building Activity objects.
 */
public class ActivityBuilder {

    public static final String DEFAULT_NAME = "deadline";
    public static final String DEFAULT_DATE = "2020-03-19";
    public static final String DEFAULT_NOTE = "Do homework";

    private Name name;
    private Date date;
    private Note note;

    /**
     * Initialise default name, date and note for an activity to test.
     */
    public ActivityBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public ActivityBuilder(Object activityToCopy) {
        Activity activity = (Activity) activityToCopy;
        name = activity.getName();
        date = activity.getDate();
        note = activity.getNote();
    }

    /**
     * Sets the {@code Name} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Build an activity accordingly.
     */
    public Activity build() {
        if (name.equals(new Name("deadline"))) {
            return new Deadline(name, date, note);
        } else if (name.equals(new Name("event"))) {
            return new Event(name, date, note);
        } else if (name.equals(new Name("lesson"))) {
            return new Lesson(name, date, note);
        } else {
            throw new NullPointerException("Failed to execute activity build.");
//            return new Activity(name, date, note) {
//                @Override
//                public void setName(Name name) {
//                    super.setName(new Name(" "));
//                }
//            };
        }

    }

}