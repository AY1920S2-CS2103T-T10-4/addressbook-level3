package nasa.logic.commands;

import static java.util.Objects.requireNonNull;
import static nasa.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;
import static nasa.model.Model.PREDICATE_SHOW_ALL_MODULES;

import nasa.model.Model;

/**
 * Lists the modules and their activity lists.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
