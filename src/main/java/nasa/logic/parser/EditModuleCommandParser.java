package nasa.logic.parser;

import static java.util.Objects.requireNonNull;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static nasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nasa.commons.core.index.Index;
import nasa.logic.commands.EditModuleCommand;
import nasa.logic.commands.EditModuleCommand.EditModuleDescriptor;
import nasa.logic.parser.exceptions.ParseException;

import java.util.List;

/**
 * Parses input arguments and creates a new EditModuleCommand object
 */
public class EditModuleCommandParser implements Parser<EditModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditModuleCommand
     * and returns an EditCommand object for execution.
     * @throws nasa.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    //TODO modify implementation
    public EditModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_MODULE_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE),
                    pe);
        }

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (isModuleCodeEdited(argMultimap.getAllValues(PREFIX_MODULE))) {
            editModuleDescriptor.setModuleCode(nasa.logic.parser.ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE_NAME).isPresent()) {
            editModuleDescriptor.setModuleName(nasa.logic.parser.ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE_NAME).get()));
        }

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new nasa.logic.parser.exceptions.ParseException(EditModuleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditModuleCommand(index, editModuleDescriptor);
    }

    /**
     * Checks if two module code prefixes are parsed, which indicates module code will be edited.
     * If non-two '/m', received, returns false
     * @param moduleCodes list containing original {@code moduleCode}(compulsory) and new {@code moduleCode}(optional)
     * @return true if exactly 2 module codes provided, otherwise false
     */
    private boolean isModuleCodeEdited(List<String> moduleCodes) {
        if (moduleCodes.size() == 2) {
            return true;
        }
        return false;
    }
}
