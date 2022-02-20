package com.cleanup.todoc.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.viewmodel.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private ProjectDataRepository projectDatasource;
    private TaskDataRepository taskDataSource;
    private Executor executor;

    public ViewModelFactory(ProjectDataRepository projectDatasource, TaskDataRepository taskDataSource, Executor executor) {
        this.projectDatasource = projectDatasource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(projectDatasource, taskDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}