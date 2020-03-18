package nasa.model.module;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import nasa.commons.core.index.Index;
import nasa.model.activity.Activity;
import nasa.model.activity.UniqueActivityList;

/**
 * Abstract class to specify fields with getter and setters for modules.
 */
public class Module {

    private ModuleCode moduleCode;
    private UniqueActivityList activityList;
    private FilteredList<Activity> filteredActivity;
    private ModuleName moduleName;

    /**
     * Constructs a {@code module}
     * @param moduleCode module code
     */
    public Module(ModuleCode moduleCode, ModuleName moduleName) {
        this.moduleCode = moduleCode;
        this.activityList = new UniqueActivityList();
        this.filteredActivity = new FilteredList<>(activityList.getActivityList());
        this.moduleName = moduleName;
    }

    //Priority priority;

    /**
     * Retrieve the moduleCode of the module.
     * @return String moduleCode
     */
    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Sets the module moduleCode to a new moduleCode.
     * Used for editing module code.
     * @param moduleCode of the module
     */
    public void setModuleCode(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    public boolean contains(Activity activity) {
        return activityList.contains(activity);
    }

    public void add(Activity toAdd) {
        activityList.add(toAdd);
    }

    public void setActivity(Activity target, Activity editedActivity) {
        activityList.setActivity(target, editedActivity);
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public void remove(Activity toRemove) {
        activityList.remove(toRemove);
    }

    public void removeActivityByIndex(Index index) {
        activityList.removeByIndex(index);
    }

    public UniqueActivityList getActivities() {
        return activityList;
    }

    public void setActivities(UniqueActivityList replacement) {
        activityList.setActivities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code activities}
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(List<Activity> activities) {
        activityList.setActivities(activities);
    }

    public Activity getActivityByIndex(Index index) {
        return activityList.getActivityByIndex(index);
    }

    public ObservableList<Activity> getFilteredActivityList() {
        return filteredActivity;
    }

    public void setActivityByIndex(Index index, Activity activity) {
        activityList.setActivityByIndex(index, activity);
    }

    public void editActivityByIndex(Index index, Object... args) {
        activityList.editActivityByIndex(index, args);
    }

    public Iterator<Activity> iterator() {
        return activityList.iterator();
    }

    public void updateFilteredActivityList(Predicate<Activity> predicate) {
        filteredActivity.setPredicate(predicate);
    }
    /**
     * Returns true if both are the same module.
     * This defines a stronger notion of equality between two activities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals(getModuleCode());
    }
}
