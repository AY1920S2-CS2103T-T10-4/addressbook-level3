package nasa.model.history;

import java.util.List;
import java.util.Stack;

/**
 * Abstract History class to store users actions.
 */
public abstract class History<T> {

    private Stack<T> stack;

    private T item;

    History() {
        item = null;
        stack = new Stack<T>();
    }

    History(Stack<T> history) {
        item = null;
        stack = history;
    }

    public void push(T item) {
        if (this.item != null) {
            stack.push(this.item);
        }
        this.item = item;
    }

    public void pushDirectly(T item) {
        stack.push(item);
    }

    public boolean pop() {
        if (!isEmpty()) {
            item = stack.pop();
            return true;
        } else {
            return false;
        }
    }

    public T popDirectly() {
        return stack.pop();
    }

    public boolean hasItem() {
        return item != null;
    }

    public T getPop() {
        return item;
    }

    public void resetItem() {
        this.item = null;
    }

    public void setStack(List<T> stack) {
        this.stack.addAll(stack);
    }

    public Stack<T> getStack() {
        return stack;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
