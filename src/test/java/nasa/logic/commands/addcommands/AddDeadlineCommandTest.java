package nasa.logic.commands.addcommands;

import static nasa.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static nasa.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS1231;
import static nasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nasa.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import nasa.logic.commands.exceptions.CommandException;
import nasa.model.Model;
import nasa.model.activity.Activity;
import nasa.model.activity.Deadline;
import nasa.model.module.Module;
import nasa.model.module.ModuleCode;
import nasa.model.module.ModuleName;
import nasa.testutil.ActivityBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import nasa.model.ModelManager;
import nasa.model.UserPrefs;
import nasa.model.NasaBook;

public class AddDeadlineCommandTest {

    private Model model;
    private Module module;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new NasaBook(), new UserPrefs());
        module = new Module(new ModuleCode(VALID_MODULE_CS1231), new ModuleName(VALID_MODULE_NAME_CS1231));
        model.addModule(module);
    }

    @Test
    public void execute_newDeadline_success() {
        Activity deadline = new ActivityBuilder().build();

        Model expectedModel = new ModelManager(new NasaBook(), new UserPrefs());
        expectedModel.addModule(new Module(new ModuleCode(VALID_MODULE_CS1231), new ModuleName(VALID_MODULE_NAME_CS1231)));
        expectedModel.addActivity(module, deadline);

        AddDeadlineCommand command = new AddDeadlineCommand((Deadline)deadline, new ModuleCode(VALID_MODULE_CS1231));
        assertCommandSuccess(command, model, String.format(AddDeadlineCommand.MESSAGE_SUCCESS, deadline), expectedModel);
    }

    @Test
    public void execute_duplicateDeadline_failure() {
        Activity deadline = new ActivityBuilder().build();
        Model expectedModel = new ModelManager(model.getNasaBook(), model.getUserPrefs());
        AddDeadlineCommand command = new AddDeadlineCommand((Deadline) deadline, new ModuleCode(VALID_MODULE_CS1231));

        expectedModel.addActivity(module, deadline);
        assertThrows(CommandException.class, AddDeadlineCommand.MESSAGE_DUPLICATED_ACTIVITY, () -> command.execute(expectedModel));
    }

    @Test
    public void constructor_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeadlineCommand(null, new ModuleCode(VALID_MODULE_CS1231)));
    }

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        Deadline deadline = (Deadline)(new ActivityBuilder().build());
        assertThrows(NullPointerException.class, () -> new AddDeadlineCommand(deadline, null));
    }
}
