package no.kristiania.projectManagement;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskDaoTest {

    private JdbcDataSource dataSource = TestDataBase.testDataSource();

    @Test
    void shouldRetriveStoredData() throws SQLException {

        TaskDao dao = new TaskDao(dataSource);
        //String task = sampleTask();
        Task task = sampleTask();
        dao.insert(task);
        assertThat(dao.listAll()).contains(task);

    }

    @Test
    public void shouldSaveAllTaskFields() throws SQLException {
        TaskDao dao = new TaskDao(dataSource);
        Task task = sampleTask();
        long id = dao.insert(task);
        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(task);
    }

    private Task sampleTask(){
        Task task = new Task();
        task.setName(sampleTaskName());

        return task;
    }

    private String sampleTaskName() {
        String[] alternatives = {
                "Get files", "Deadline til 8pm", "meeting"
        };

        return alternatives[new Random().nextInt(alternatives.length)];
    }


}
