package com.cleanup.todoc.databaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.Dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TaskDaoTest {
    // FOR DATA
    private TodocDatabase database;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }


    // DATA SET FOR TEST
    private static final long PROJECT_ID = 4L;

    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "Cupidon", 0xFFA3CED2);


    @Test
    public void insertAndGetProject() throws InterruptedException {

        // BEFORE : Adding a new project
        this.database.projectDao().insertProject(PROJECT_DEMO);


        // TEST
        Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);

    }


    private static final Task NEW_TASK_SHOPPING = new Task(1, 4L, "Faire les courses", new Date().getTime());

    private static final Task NEW_TASK_CHILDRENS = new Task(2, 4L, "Chercher les enfants", new Date().getTime());

    private static final Task NEW_TASK_STUDY = new Task(3, 4L, "Etudier", new Date().getTime());


    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetItems() throws InterruptedException {


        // BEFORE : Adding demo projects & demo tasks
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK_SHOPPING);
        this.database.taskDao().insertTask(NEW_TASK_CHILDRENS);
        this.database.taskDao().insertTask(NEW_TASK_STUDY);


        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertEquals(3, tasks.size());
    }


    @Test
    public void insertAndDeleteItem() throws InterruptedException {


        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.


        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK_SHOPPING);

        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID)).get(0);
        this.database.taskDao().deleteTask(taskAdded);


        //TEST


        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(tasks.isEmpty());


    }
}

