package nasa.logic.commands.addcommands;

import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_DATE;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_NOTE;
import static nasa.logic.parser.CliSyntax.PREFIX_PRIORITY;

import nasa.model.activity.Deadline;
import nasa.model.module.ModuleCode;

/**
 * Adds a deadline task to a module's list.
 */
public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the module's activity list. "
            + "Parameters:"
            + PREFIX_MODULE + "MODULE CODE"
            + PREFIX_DATE + "DATE"
            + PREFIX_ACTIVITY_NAME + "ACTIVITY NAME"
            + PREFIX_PRIORITY + "PRIORITY"
            + PREFIX_NOTE + "NOTE" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS3233"
            + PREFIX_DATE + "2020-03-20"
            + PREFIX_ACTIVITY_NAME + "SEA Group Programming Assignment"
            + PREFIX_PRIORITY + "1"
            + PREFIX_NOTE + "Focus on computational geometry and DP.";

    /**
     * Creates an AddCommand that adds {@code deadline} to list of {@code moduleCode}.
     * @param deadline Deadline task to be added
     * @param moduleCode Module where deadline is to be added.
     */
    public AddDeadlineCommand(Deadline deadline, ModuleCode moduleCode) {
       super(deadline, moduleCode);
    }
}
