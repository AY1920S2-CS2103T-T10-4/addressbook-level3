package nasa.ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import nasa.logic.commands.Command;
import nasa.logic.commands.CommandResult;
import nasa.logic.commands.exceptions.CommandException;
import nasa.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory;
    private ListIterator<String> commandHistoryIterator;;

    @FXML
    private TextField commandTextField;

    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandHistory = new LinkedList<String>();
        commandHistoryIterator = commandHistory.listIterator();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            //Overriding default redo
            if (event.getCode() == KeyCode.Z && event.isShortcutDown() && event.isShiftDown()) {
                event.consume();
                commandTextField.setText("redo");
                handleCommandEntered();
            //Overriding default undo
            } else if (event.getCode() == KeyCode.Z && event.isShortcutDown()) {
                event.consume();
                commandTextField.setText("undo");
                handleCommandEntered();
            }
        });
        //Controls to view command history
        commandTextField.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                switch (key.getCode()) {
                    case UP:
                        if (commandHistoryIterator.hasPrevious()) {
                            commandTextField.setText(commandHistoryIterator.previous());
                        }
                        break;
                    case DOWN:
                        if (commandHistoryIterator.hasNext()) {
                            commandTextField.setText(commandHistoryIterator.next());
                        }
                        break;
                    case H:
                        if (key.isControlDown()) {
                            commandTextField.setText("help");
                            handleCommandEntered();
                        }
                        break;
                    default:
                        break;

                }
        });
    }
    public static void runSafe(final Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable");
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        }
        else {
            Platform.runLater(runnable);
        }
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandHistory.add(commandTextField.getText());
            commandHistoryIterator = commandHistory
                    .listIterator(commandHistory.size());
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see nasa.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
