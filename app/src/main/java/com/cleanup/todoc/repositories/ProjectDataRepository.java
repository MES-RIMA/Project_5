package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.Dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {
    private final ProjectDao projectDao;

        public ProjectDataRepository(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        // --- GET  ALL PROJECT ---

        public LiveData<List<Project>> getProjects() {
            return this.projectDao.getProjects();
        }
        // Get a project
        public LiveData<Project> getProject(long projectId) { return this.projectDao.getProject(projectId);}

}
