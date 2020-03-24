package nasa.model.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class that manage all histories.
 * @param <T>
 */
public class ModuleListHistory<T> extends History<T> {

    public ModuleListHistory() {
        super();
    }

    public ModuleListHistory(Stack<T> moduleListStack) {
        super(moduleListStack);
    }

    /**
     * Reset the stack list with {@moduleListHistory}
     * @param moduleListHistory
     */
    public void setStackList(List<T> moduleListHistory) {
        super.setStack(moduleListHistory);
    }

    /**
     * Return unmodifiable list containing histories.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        List<T> list = new ArrayList<>(super.getStack());
        ObservableList<T> newList = FXCollections.observableArrayList(list);
        return newList;
    }

}
