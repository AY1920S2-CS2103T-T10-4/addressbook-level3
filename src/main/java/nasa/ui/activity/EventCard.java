package nasa.ui.activity;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import nasa.model.activity.Event;
import nasa.ui.UiPart;


/**
 * An UI component that displays information of a {@code Module}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;
    @FXML
    private HBox eventPane;
    @FXML
    private Label index;
    @FXML
    private Label name;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label note;
    @FXML
    private Label status;

    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        index.setText(String.valueOf(displayedIndex));
        name.setText(event.getName().toString());
        startDate.setText("From " + event.getStartDate().toString());
        endDate.setText("To " + event.getEndDate().toString());
        note.setText(event.getNote().toString());
        //status.setText(event.getStatus().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return name.getText().equals(card.name.getText());
    }
}
