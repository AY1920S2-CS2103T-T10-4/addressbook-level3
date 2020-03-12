package nasa.logic.commands.addcommands;

<<<<<<< HEAD:src/main/java/nasa/logic/commands/addcommands/AddEventCommand.java
import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_DATE;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_NOTE;
import static nasa.logic.parser.CliSyntax.PREFIX_PRIORITY;
=======
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_DATE;
import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static nasa.logic.parser.CliSyntax.PREFIX_NOTE;
>>>>>>> cf98652144e57734b46653f44f8db289a6e30a49:src/main/java/NASA/logic/commands/addcommands/AddEventCommand.java

import nasa.model.activity.Event;
import nasa.model.module.ModuleCode;

public class AddEventCommand extends AddCommand {

    public final static String COMMAND_WORD = "event";

    public final static String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the module's activity list. "
            + "Parameters:"
            + PREFIX_MODULE + "MODULE CODE"
            + PREFIX_DATE + "DATE"
            + PREFIX_ACTIVITY_NAME + "ACTIVITY NAME"
            + PREFIX_PRIORITY + "PRIORITY"
            + PREFIX_NOTE + "NOTE" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS3233"
            + PREFIX_DATE + "2020-04-12"
            + PREFIX_ACTIVITY_NAME + "CP contest"
            + PREFIX_PRIORITY + "1"
            + PREFIX_NOTE + "Remember to study content before coming.";

    public AddEventCommand(Event event, ModuleCode moduleCode) {
        super(event, moduleCode);
    }
}
