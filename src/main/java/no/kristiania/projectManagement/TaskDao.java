package no.kristiania.projectManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends AbstractDao<Task> {

    public TaskDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertTask(Task task, PreparedStatement statement) throws SQLException {
        statement.setString(1, task.getName());
    }

    @Override
    protected Task readTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setName(rs.getString("name"));
        return task;
    }

    public long insert(Task task) throws SQLException {
        return insert(task, "insert into tasks (name) values(?)");
    }

    public List<Task> listAll() throws SQLException {
        return listAll("select * from tasks");
    }


    public Task retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("select * from tasks where id =?")) {
                statement.setLong(1, id);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return readTask(rs);
                    } else {
                        return null;
                    }

                }
            }
        }

    }
}

