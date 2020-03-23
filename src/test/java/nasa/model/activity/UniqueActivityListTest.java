package nasa.model.activity;

import javafx.collections.ObservableList;

import static nasa.testutil.TypicalActivities.CORRECT_EVENT;
import static nasa.testutil.TypicalActivities.DEADLINE;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * To test the UniqueActivity class.
 */
class UniqueActivityListTest {

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();
    private final UniqueActivityList newUniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void contains_activityNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(DEADLINE));
    }

    @Test
    public void contains_activityInList_returnsTrue() {
        uniqueActivityList.add(DEADLINE);
        assertTrue(uniqueActivityList.contains(DEADLINE));
    }

    @Test
    public void setActivity_test() {
        uniqueActivityList.add(DEADLINE);
        uniqueActivityList.setActivity(DEADLINE, CORRECT_EVENT);
        assertTrue(uniqueActivityList.contains(CORRECT_EVENT));
    }

    @Test
    public void remove_test() {
        uniqueActivityList.add(DEADLINE);
        uniqueActivityList.remove(DEADLINE);
        assertFalse(uniqueActivityList.contains(DEADLINE));
    }

    @Test
    public void setActivities_test() {
        uniqueActivityList.add(DEADLINE);
        uniqueActivityList.add(CORRECT_EVENT);

        newUniqueActivityList.add(CORRECT_EVENT);
        uniqueActivityList.setActivities(newUniqueActivityList);

        assertFalse(uniqueActivityList.contains(DEADLINE));
    }

    @Test
    public void checkUnmodifiableList_test() {
        uniqueActivityList.add(DEADLINE);
        uniqueActivityList.add(CORRECT_EVENT);
        assertTrue(uniqueActivityList.asUnmodifiableObservableList().size() == 2);
    }

    @Test
    public void modifyUnmodifiableList_test() {
        uniqueActivityList.add(DEADLINE);
        ObservableList<Activity> temp = uniqueActivityList.asUnmodifiableObservableList();
        assertThrows(UnsupportedOperationException.class, () -> temp.add(CORRECT_EVENT));
    }

}
