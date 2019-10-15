package no.kristiania.projectManagement;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class TaskDao {

    private DataSource dataSource;

    public TaskDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertTask(String task) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into tasks (name) values (?)"
            );
            statement.setString(1, task);
            statement.executeUpdate();
        }
    }

    public List<String> listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from tasks"
            )) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<String> result = new ArrayList<>();

                    while (rs.next()) {
                        result.add(rs.getString("name"));
                    }

                    return result;
                }

            }
        }

    }

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Enter a task name to insert: ");
        String taskName = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("projectmanager.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/projectmanager");
        dataSource.setUser("projectmanager");
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        TaskDao taskDao = new TaskDao(dataSource);
        taskDao.insertTask(taskName);

        System.out.println(taskDao.listAll());
    }

}
