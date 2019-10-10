package no.kristiania.projectManagement;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class taskTest{

    @Test
    void shouldRetriveStoredData(){
        taskDao dao = new taskDao();
        String taskName = pickOne (new String[] {"change status", "get files", "add new project members"});
        dao.insertTask(taskName);
        assertThat(dao.listAll()).contains(taskName);

    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];
    }


}
