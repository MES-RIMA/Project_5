package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.Dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository { private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // --- GET ---

    public LiveData<List<Task>> getAllTasks() {
        return taskDao.getAllTasks();
    }

    // Get Sorted Tasks

    public LiveData<List<Task>> getAllTasksOrderByNameAsc() {
        return taskDao.getAllTasksOrderByNameAsc();
    }

    public LiveData<List<Task>> getAllTasksOrderByNameDesc() {
        return taskDao.getAllTasksOrderByNameDesc();
    }

    public LiveData<List<Task>> getAllTasksOrderByTimeAsc() {
        return taskDao.getAllTasksOrderByTimeAsc();
    }

    public LiveData<List<Task>> getAllTasksOrderByTimeDesc() {
        return taskDao.getAllTasksOrderByTimeDesc();
    }

    // --- CREATE ---

    public void createTask(Task task) {
        taskDao.insertTask(task);
    }

    // --- DELETE ---

    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }

}


