package no.kristiania.projectManagement;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskDaoTest {

    @Test
    void shouldRetriveStoredData() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase;DB_CLOSE_DELAY=-1");

        Flyway.configure().dataSource(dataSource).load().migrate();

        TaskDao dao = new TaskDao(dataSource);
        String task = sampleTask();
        dao.insert(task);
        assertThat(dao.listAll()).contains(task);

    }

    private String sampleTask() {
        String[] alternatives = {
                "Get files", "Deadline til 8pm", "meeting"
        };

        return alternatives[new Random().nextInt(alternatives.length)];
    }


}
