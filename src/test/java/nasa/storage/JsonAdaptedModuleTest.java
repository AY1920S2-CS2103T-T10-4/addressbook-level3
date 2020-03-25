package nasa.storage;

import static nasa.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static nasa.testutil.Assert.assertThrows;
import static nasa.testutil.TypicalModules.CS2103T;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nasa.commons.exceptions.IllegalValueException;
import nasa.model.module.Module;
import nasa.model.module.ModuleCode;
import nasa.model.module.ModuleName;

public class JsonAdaptedModuleTest {
    private static final String INVALID_CODE = "CS@";
    private static final String INVALID_NAME = "#name";
    //private static final String INVALID_ACTIVITY = "#friend";

    private static final String VALID_CODE = "CS2103T";
    private static final String VALID_NAME = CS2103T.getModuleCode().toString();
    private static final List<JsonAdaptedActivity> VALID_ACTIVITIES = CS2103T.getActivities().getActivityList().stream()
            .map(JsonAdaptedActivity::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T);
        assertEquals(CS2103T, module.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_CODE, VALID_NAME, VALID_ACTIVITIES);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_NAME, VALID_ACTIVITIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_CODE, INVALID_NAME, VALID_ACTIVITIES);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, null, VALID_ACTIVITIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    /*
    @Test
    public void toModelType_invalidActivities_throwsIllegalValueException() {
        List<JsonAdaptedActivity> invalidActivities = new ArrayList<>(VALID_ACTIVITIES);
        invalidActivities.add(new JsonAdaptedActivity(INVALID_ACTIVITY));
        JsonAdaptedModule Module =
                new JsonAdaptedModule(VALID_CODE, VALID_NAME, invalidActivities);
        assertThrows(IllegalValueException.class, Module::toModelType);
    }
     */

}
