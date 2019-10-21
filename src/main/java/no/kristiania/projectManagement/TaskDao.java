package no.kristiania.projectManagement;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDao extends AbstractDao<String> {

    public TaskDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertTask(String task, PreparedStatement statement) throws SQLException {
        statement.setString(1, task);
    }

    @Override
    protected String readTask(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }

    public void insert(String task) throws SQLException {
        insert(task, "insert into tasks (name) values(?)");
    }

    public List<String> listAll() throws SQLException {
        return listAll("select * from tasks");
    }


}

