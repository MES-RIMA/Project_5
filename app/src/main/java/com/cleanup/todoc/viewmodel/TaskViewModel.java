package com.cleanup.todoc.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // Repositories
    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executor;


    // Data
    @Nullable
    private LiveData<List<Project>> mProjects;

    private SortMethod sortMethod = SortMethod.NONE;


    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }


    public void init() {
        // if (this.mProjects == null)
        if (this.mProjects != null){
            // mProjects = projectDataSource.getProjects();
            return;
    }

    mProjects =projectDataSource.getProjects();
}
  // For Project
    //--------------------
    @Nullable
    public LiveData<List<Project>> getProjects() {
        return this.mProjects;
    }
// For Task
   public LiveData<List<Task>>getTask(long projectId){
        return taskDataSource.getTasks(projectId);
   }
    // For Task
    public LiveData<List<Task>> getAllTasks() {
        switch (sortMethod) {
            case NONE:
                return taskDataSource.getAllTasks();
            case OLD_FIRST:
                return taskDataSource.getAllTasksOrderByTimeAsc();
            case RECENT_FIRST:
                return taskDataSource.getAllTasksOrderByTimeDesc();
            case ALPHABETICAL:
                return taskDataSource.getAllTasksOrderByNameAsc();
            case ALPHABETICAL_INVERTED:
                return taskDataSource.getAllTasksOrderByNameDesc();

        }
        return taskDataSource.getAllTasks();
    }

    // For Tasks sorted
    public LiveData<List<Task>> getAllTasksOrderByNameAsc() {
        return taskDataSource.getAllTasksOrderByNameAsc();
    }

    public LiveData<List<Task>> getAllTasksOrderByNameDesc() {
        return taskDataSource.getAllTasksOrderByNameDesc();
    }

    public LiveData<List<Task>> getAllTasksOrderByTimeAsc() {
        return taskDataSource.getAllTasksOrderByTimeAsc();
    }

    public LiveData<List<Task>> getAllTasksOrderByTimeDesc() {
        return taskDataSource.getAllTasksOrderByTimeDesc();
    }


    public void createTask(Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
    }


    public void setSort(SortMethod alphabetical) {
        sortMethod = alphabetical;
    }

    public enum SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,
        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,
        /**
         * Lastly created first
         */
        RECENT_FIRST,
        /**
         * First created first
         */
        OLD_FIRST,
        /**
         * No sort
         */
        NONE
    }
}

