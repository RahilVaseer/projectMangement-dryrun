package no.kristiania.projectManagement;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {

    @Test
    void shouldRetriveStoredData() throws SQLException {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:test");

        dataSource.getConnection().createStatement().executeUpdate(
                "create table tasks (name varchar(100))"
        );
        TaskDao dao = new TaskDao(dataSource);
        String taskName = pickOne (new String[] {"change status", "get files", "add new project members"});
        dao.insertTask(taskName);
        assertThat(dao.listAll()).contains(taskName);

    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];

    }


}
