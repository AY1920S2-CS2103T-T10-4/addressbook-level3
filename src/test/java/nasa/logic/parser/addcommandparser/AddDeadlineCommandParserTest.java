package nasa.logic.parser.addcommandparser;

import static nasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nasa.logic.commands.CommandTestUtil.*;
import static nasa.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nasa.logic.parser.CommandParserTestUtil.assertParseSuccess;

import nasa.logic.commands.addcommands.AddDeadlineCommand;
import nasa.model.activity.Date;
import nasa.model.activity.Deadline;
import nasa.model.activity.Name;
import nasa.model.activity.Note;
import nasa.model.activity.Priority;
import nasa.model.activity.Status;
import nasa.model.module.ModuleCode;
import org.junit.jupiter.api.Test;

public class AddDeadlineCommandParserTest {

    private AddDeadlineCommandParser parser = new AddDeadlineCommandParser();
    private ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CS1231);

    @Test
    public void parse_allFieldPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, new AddDeadlineCommand(DeadlineBuilder.allFieldsPresent, moduleCode));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE);

        // missing moduleCode
        assertParseFailure(parser, ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, expectedMessage);

        // missing activity name
        assertParseFailure(parser, MODULE_DESC_CS1231 + DATE_DESC_TEST + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, expectedMessage);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // notes parameter missing
        assertParseSuccess(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST
                + PRIORITY_DESC_HIGH, new AddDeadlineCommand(DeadlineBuilder.noteFieldMissing,
                moduleCode));

        // priority parameter missing
        assertParseSuccess(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + NOTES_DESC_TEST
                , new AddDeadlineCommand(DeadlineBuilder.priorityFieldMissing, moduleCode));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, INVALID_MODULE_DESC + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid activity name
        assertParseFailure(parser, MODULE_DESC_CS1231 + INVALID_ACTIVITY_NAME_DESC + DATE_DESC_TEST + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + INVALID_DATE_DESC + NOTES_DESC_TEST
                + PRIORITY_DESC_HIGH, Date.MESSAGE_CONSTRAINTS);

        // invalid Notes
        assertParseFailure(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + INVALID_NOTES_DESC
                + PRIORITY_DESC_HIGH, Note.MESSAGE_CONSTRAINTS);

        // invalid Priority
        assertParseFailure(parser, MODULE_DESC_CS1231 + ACTIVITY_NAME_DESC_HWK + DATE_DESC_TEST + NOTES_DESC_TEST
                + INVALID_PRIORITY_DESC, Priority.PRIORITY_RANGE_CONSTRAINTS);
    }
}

class DeadlineBuilder {
    public static Deadline allFieldsPresent = new Deadline(new Name(VALID_ACTIVITY_NAME_HWK), new Date(VALID_DATE_TEST),
            new Note(VALID_NOTES_TEST), Status.ONGOING ,new Priority(VALID_PRIORITY_HIGH));
    public static Deadline noteFieldMissing = new Deadline(new Name(VALID_ACTIVITY_NAME_HWK), new Date(VALID_DATE_TEST),
            null, Status.ONGOING ,new Priority(VALID_PRIORITY_HIGH));
    public static Deadline priorityFieldMissing = new Deadline(new Name(VALID_ACTIVITY_NAME_HWK), new Date(VALID_DATE_TEST),
            new Note(VALID_NOTES_TEST), Status.ONGOING , null);
}
