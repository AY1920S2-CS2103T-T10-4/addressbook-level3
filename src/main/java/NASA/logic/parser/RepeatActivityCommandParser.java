package nasa.logic.parser;

import static nasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_DATE;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_NOTE;
import static nasa.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static nasa.logic.parser.CliSyntax.PREFIX_REPEAT;

import java.util.stream.Stream;

import nasa.commons.core.index.Index;
import nasa.logic.commands.RepeatCommand;
import nasa.logic.commands.addcommands.AddDeadlineCommand;
import nasa.logic.parser.exceptions.ParseException;
import nasa.model.activity.Date;
import nasa.model.activity.Deadline;
import nasa.model.activity.Name;
import nasa.model.activity.Note;
import nasa.model.activity.Priority;
import nasa.model.activity.Schedule;
import nasa.model.module.ModuleCode;

public class RepeatActivityCommandParser implements Parser<RepeatCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDeadlineCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RepeatCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE,
                        PREFIX_ACTIVITY_NAME, PREFIX_REPEAT);

        Prefix prefixes[] = {PREFIX_MODULE, PREFIX_ACTIVITY_NAME, PREFIX_REPEAT};
        boolean arePrefixesPresent = Stream.of(prefixes).allMatch(prefix -> argMultimap.getValue(prefix).isPresent());

        if (!arePrefixesPresent
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RepeatCommand.MESSAGE_USAGE));
        }

        // compulsory fields
        Name activityName = ParserUtil.parseActivityName(argMultimap.getValue(PREFIX_ACTIVITY_NAME).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
        Index type = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_REPEAT).get());
        return new RepeatCommand(moduleCode, activityName, type);
    }
}
