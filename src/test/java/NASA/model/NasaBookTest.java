package NASA.model;

import NASA.commons.core.index.Index;
import NASA.model.module.UniqueModuleList;
import org.junit.jupiter.api.Test;

import static NASA.testutil.TypicalActivities.DEADLINE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static NASA.testutil.TypicalModules.CS2103T;
import static NASA.testutil.TypicalModules.CS2106;

class NasaBookTest {
    private final NasaBook nasaBook = new NasaBook();
    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    void addModule() {
        nasaBook.addModule(CS2103T);
        assertTrue(nasaBook.hasModule(CS2103T));
    }

    @Test
    void addActivity() {
        nasaBook.addModule(CS2103T);
        nasaBook.addActivity(CS2103T, DEADLINE);
        assertTrue(nasaBook.hasActivity(CS2103T,DEADLINE));
    }

    @Test
    void removeActivity() {
        nasaBook.addModule(CS2103T);
        nasaBook.addActivity(CS2103T, DEADLINE);
        nasaBook.removeActivity(CS2103T, DEADLINE);
        assertFalse(nasaBook.hasActivity(CS2103T, DEADLINE));
    }

    @Test
    void removeModuleByIndex() {
        nasaBook.addModule(CS2103T);
        nasaBook.addModule(CS2106);
        nasaBook.removeModuleByIndex(new Index(1));
        assertTrue(nasaBook.hasModule(CS2103T));
        assertFalse(nasaBook.hasModule(CS2106));
    }
}
