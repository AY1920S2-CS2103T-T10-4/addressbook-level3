package nasa.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import nasa.commons.core.index.Index;
import nasa.model.activity.Activity;
import nasa.model.activity.Name;
import nasa.model.activity.UniqueActivityList;
import nasa.model.module.Module;
import nasa.model.module.ModuleCode;
import nasa.model.module.ModuleName;
import nasa.model.module.UniqueModuleList;

/**
 * Wraps all data at the Nasa Book Level
 * Duplicates are not allowed (by .isSameActivity comparison)
 */
public class NasaBook implements ReadOnlyNasaBook {

    private final UniqueModuleList moduleList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        moduleList = new UniqueModuleList();
    }

    public NasaBook() {}

    /**
     * Creates a NasaBook using the moduleList in the {@code toBeCopied}
     */
    public NasaBook(ReadOnlyNasaBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the activities of module {@moduleCode} with {@code activities}
     * {@code activities} must not contain duplicate activities.
     */
    public UniqueActivityList getActivities(Module module) {
        return moduleList.getActivities(module);
    }

    /**
     * Replaces the contents of the activities of module {@moduleCode} with {@code activities}
     * {@code activities} must not contain duplicate activities.
     */
    public UniqueActivityList getActivities(ModuleCode moduleCode) {
        return moduleList.getActivities(moduleCode);
    }

    /**
     * Replaces the contents of the activities of module {@moduleCode} with {@code activities}
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(Module module, List<Activity> activities) {
        Module toEditModule = moduleList.getModule(module);
        toEditModule.setActivities(activities);
        moduleList.setModule(module, toEditModule);
    }

    /**
     * Replaces the contents of the activities of module {@moduleCode} with {@code activities}
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(ModuleCode module, List<Activity> activities) {
        Module toEditModule = moduleList.getModule(module);
        toEditModule.setActivities(activities);
        moduleList.setModule(module, toEditModule);
    }

    /**
     * Replaces current module list with another module list.
     * @param moduleList must not be empty
     */
    public void setModuleList(UniqueModuleList moduleList) {
        requireNonNull(moduleList);

        this.moduleList.setModules(moduleList);
    }

    /**
     * Add a single activity to module {@moduleCode} with {@code activity}
     * {@code activity} must not contain duplicate activities.
     */
    public void addActivity(Module module, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(module);
        toEditModule.add(activity);
        moduleList.setModule(module, toEditModule);
    }

    /**
     * Add a single activity to module {@moduleCode} with {@code activity}
     * {@code activity} must not contain duplicate activities.
     */
    public void addActivity(ModuleCode moduleCode, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(moduleCode);
        toEditModule.add(activity);
    }

    /**
     * Remove a single activity from module {@code module} with {@code activity}
     * {@code activity} must exist in the list.
     */
    public void removeActivity(Module module, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(module);
        toEditModule.remove(activity);
        moduleList.setModule(module, toEditModule);
    }

    /**
     * Remove a single activity from module {@code module} with {@code activity}
     * {@code activity} must exist in the list.
     */
    public void removeActivity(ModuleCode moduleCode, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(moduleCode);
        toEditModule.remove(activity);
        moduleList.setModule(moduleCode, toEditModule);
    }

    /**
     * Remove module by index.
     * @param index must not be negative.
     */
    public void removeModuleByIndex(Index index) {
        moduleList.removeByIndex(index);
    }

    /**
     * Remove activity by index.
     * @param index must not be negative.
     */
    public void removeActivityByIndex(Module module, Index index) {
        requireNonNull(module);

        Module toEditModule = moduleList.getModule(module);
        toEditModule.removeActivityByIndex(index);
    }

    /**
     * Remove activity by index.
     * @param index must not be negative.
     */
    public void removeActivityByIndex(ModuleCode moduleCode, Index index) {
        requireNonNull(moduleCode);

        Module toEditModule = moduleList.getModule(moduleCode);
        toEditModule.removeActivityByIndex(index);
    }

    /**
     * Check if it has activity {@code activity} in {@code module}
     */
    public boolean hasActivity(Module module, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(module);
        return toEditModule.contains(activity);
    }

    /**
     * Check if it has activity {@code activity} in {@code module}
     */
    public boolean hasActivity(ModuleCode moduleCode, Activity activity) {
        requireNonNull(activity);

        Module toEditModule = moduleList.getModule(moduleCode);
        return toEditModule.contains(activity);
    }

    /**
     * Resets the existing data of this {@code NasaBook} with {@code newData}
     */
    public void resetData(ReadOnlyNasaBook newData) {
        requireNonNull(newData);

        moduleList.setModules(newData.getModuleList());
    }

    //// module-Level operations

    /**
     * Returns true if an module has the same identity as {@code module} exits in NasaBook.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.contains(module);
    }

    /**
     * Returns true if an module has the same identity as {@code module} exits in NasaBook.
     */
    public boolean hasModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return moduleList.contains(moduleCode);
    }

    /**
     * Adds an module to the NasaBook.
     * The module must not already exist in the NasaBook
     */
    public void addModule(Module module) {
        moduleList.add(module);
    }

    /**
     * Adds an module to the NasaBook.
     * The module must not already exist in the NasaBook
     */
    public void addModule(ModuleCode moduleCode, ModuleName moduleName) {
        moduleList.add(moduleCode, moduleName);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedActivity}.
     * {@code target} must exit in the NasaBook.
     * The module identity of {@code editedActivity} must not be the same as another existing module in Nasa Book.
     */
    public void setModule(Module target, Module editedActivity) {
        requireNonNull(editedActivity);

        moduleList.setModule(target, editedActivity);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedActivity}.
     * {@code target} must exit in the NasaBook.
     * The module identity of {@code editedActivity} must not be the same as another existing module in Nasa Book.
     */
    public void setModule(ModuleCode target, Module editedActivity) {
        requireNonNull(editedActivity);

        moduleList.setModule(target, editedActivity);
    }

    public Activity getActivityByIndex(Module module, Index index) {
        return moduleList.getModule(module).getActivityByIndex(index);
    }

    public Activity getActivityByIndex(ModuleCode module, Index index) {
        return moduleList.getModule(module).getActivityByIndex(index);
    }

    public void setActivityByIndex(Module module, Index index, Activity activity) {
        requireNonNull(activity);

        moduleList.setActivityByIndex(module, index, activity);
    }

    public void setActivityByIndex(ModuleCode moduleCode, Index index, Activity activity) {
        requireNonNull(activity);

        moduleList.setActivityByIndex(moduleCode, index, activity);
    }

    /**
     * Edits activity via index.
     * @param module module where the activity belongs
     * @param index index of the activity in the module activity list
     * @param args parameters to be edited
     */
    public void editActivityByIndex(Module module, Index index, Object... args) {
        requireNonNull(args);

        moduleList.editActivityByIndex(module, index, args);
    }

    /**
     * Edits activity via index.
     * @param moduleCode moduleCode of the activity
     * @param index index of the activity in the module activity list
     * @param args parameters to be edited
     */
    public void editActivityByIndex(ModuleCode moduleCode, Index index, Object... args) {
        requireNonNull(args);

        moduleList.editActivityByIndex(moduleCode, index, args);
    }

    /**
     * Removes {@code key} from this {@code NasaBook}.
     * {@code key} must exist in the Nasa Book.
     */
    public void removeModule(Module key) {
        moduleList.remove(key);
    }

    /**
     * Removes {@code key} from this {@code NasaBook}.
     * {@code key} must exist in the Nasa Book.
     */
    public void removeModule(ModuleCode key) {
        moduleList.remove(key);
    }

    public UniqueModuleList getList() {
        return moduleList;
    }

    public void setSchedule(ModuleCode module, Name activity, Index type) {
        moduleList.getModule(module).getActivityByName(activity).setSchedule(type.getZeroBased());
    }
    //// util methods

    @Override
    public String toString() {
        return moduleList.asUnmodifiableObservableList().size() + " moduleList";
        //TODO: refine Later
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return moduleList.asUnmodifiableObservableList();
    }

    /**
     * Ensure that the class being extracted does not points to the same object.
     */
    @Override
    public ObservableList<Module> getDeepCopyList() {
        ObservableList<Module> deepCopyList = FXCollections.observableArrayList();
        for (Module mods : moduleList.asUnmodifiableObservableList()) {
            Module moduleTemp = new Module(mods.getModuleCode(), mods.getModuleName());
            moduleTemp.setActivities(mods.getActivities());
            deepCopyList.add(moduleTemp);
        }
        return deepCopyList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NasaBook
                && moduleList.equals(((NasaBook) other).moduleList));
    }

    @Override
    public int hashCode() {
        return moduleList.hashCode();
    }
}
