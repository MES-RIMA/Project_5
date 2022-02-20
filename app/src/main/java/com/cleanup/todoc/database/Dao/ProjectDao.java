package com.cleanup.todoc.database.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    // Get all projects
    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getProjects();

    @Query("SELECT * FROM  project_table WHERE id = :projectId")
    LiveData<Project> getProject(long projectId);

    @Insert
    long insertProject(Project project);
}
