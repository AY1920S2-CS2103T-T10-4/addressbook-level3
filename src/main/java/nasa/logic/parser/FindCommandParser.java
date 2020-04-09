package nasa.logic.parser;

import static nasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.Arrays;

import nasa.logic.commands.FindCommand;
import nasa.logic.parser.exceptions.ParseException;
import nasa.model.activity.ActivityContainsKeyWordsPredicate;
import nasa.model.activity.Name;
import nasa.model.module.ModuleCode;
import nasa.model.module.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new ActivityContainsKeyWordsPredicate(Arrays.asList(nameKeywords)));
    }
}
