package nasa.model.module;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static nasa.testutil.TypicalModules.CS2103T;
import static nasa.testutil.TypicalModules.CS2106;
import static nasa.testutil.TypicalModules.GEH1001;
import static nasa.testutil.TypicalActivities.DEADLINE;

import nasa.commons.core.index.Index;
import nasa.model.activity.Activity;
import nasa.model.activity.Note;
import org.junit.jupiter.api.Test;

class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();
    private final UniqueModuleList newUniqueModuleList = new UniqueModuleList();

    @Test
    void contains() {
        uniqueModuleList.add(CS2103T);
        assertTrue(uniqueModuleList.contains(CS2103T));
    }

    @Test
    void setModule() {
    }

    @Test
    void remove() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.remove(CS2103T);
        assertFalse(uniqueModuleList.contains(CS2103T));
    }

    @Test
    void setModules() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.setModule(CS2103T, CS2106);
        assertFalse(uniqueModuleList.contains(CS2103T));
        assertTrue(uniqueModuleList.contains(CS2106));
    }

    @Test
    void testSetModules() {
        newUniqueModuleList.add(GEH1001);
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.setModules(newUniqueModuleList);
        assertTrue(uniqueModuleList.contains(GEH1001));
        assertFalse(uniqueModuleList.contains(CS2103T));
    }

    @Test
    void setActivityByIndex() {
        DEADLINE.setNote(new Note("HELLO WORLD"));
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.add(GEH1001);
        //Task 2 of GEH1001 notes is "Final Essay"
        Index index = new Index(2);
        uniqueModuleList.setActivityByIndex(GEH1001, index, DEADLINE);
        assertTrue(uniqueModuleList.getModule(GEH1001).contains(DEADLINE));
    }

    @Test
    void editActivityByIndex() {
        uniqueModuleList.add(CS2103T);
        uniqueModuleList.add(GEH1001);
        //Task 2 of GEH1001 notes is "Final Essay"
        Note note = new Note("Hello world");
        Index index = new Index(2);
        uniqueModuleList.editActivityByIndex(GEH1001, index, note);
        Activity activity = uniqueModuleList.getModule(GEH1001).getActivityByIndex(index);
        assertTrue(activity.getNote().equals(note));
    }

    @Test
    void getModule() {
        uniqueModuleList.add(CS2103T);
        assertTrue(CS2103T.equals(uniqueModuleList.getModule(CS2103T)));
        assertFalse(CS2106.equals(uniqueModuleList.getModule(CS2103T)));
    }
}
