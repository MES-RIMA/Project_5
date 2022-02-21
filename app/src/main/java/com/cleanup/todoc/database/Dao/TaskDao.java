package com.cleanup.todoc.database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Create new Task
    @Insert
    void insertTask(Task task);

    // Get task from project
    @Query("SELECT * FROM task_table WHERE projectId = :projectId")
    LiveData<List<Task>> getTasks(long projectId);

    // Get all tasks
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    // Sorted Tasks
    @Query("SELECT * FROM task_table ORDER BY name ASC ")
    LiveData<List<Task>> getAllTasksOrderByNameAsc();

    @Query("SELECT * FROM task_table ORDER BY name DESC")
    LiveData<List<Task>> getAllTasksOrderByNameDesc();

    @Query("SELECT * FROM task_table ORDER BY creationTimestamp ASC ")
    LiveData<List<Task>> getAllTasksOrderByTimeAsc();

    @Query("SELECT * FROM task_table ORDER BY creationTimestamp DESC ")
    LiveData<List<Task>> getAllTasksOrderByTimeDesc();

    // delete Task
    @Delete
    void deleteTask(Task task);
}

