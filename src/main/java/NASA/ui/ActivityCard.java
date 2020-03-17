package nasa.ui;

import NASA.model.activity.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import NASA.model.module.Module;
import javafx.scene.layout.VBox;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ActivityCard extends NASA.ui.UiPart<Region> {

    private static final String FXML = "ActivityListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Activity activity;
    @FXML
    private VBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label date;


    public ActivityCard(Activity activity, int displayedIndex) {
        super(FXML);
        this.activity = activity;
        name.setText(activity.getName().toString());
        date.setText(activity.getDate().toString());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ActivityCard)) {
            return false;
        }

        // state check
        ActivityCard card = (ActivityCard) other;
        return name.getText().equals(card.name.getText())
                && date.equals(card.date);
    }
}
