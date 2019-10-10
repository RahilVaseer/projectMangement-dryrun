package no.kristiania.projectManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class taskDao {

    private List<String> tasks = new ArrayList<>();

    public void insertTask(String task) {
        tasks.add(task);
    }

    public List<String> listAll() {
        return Collections.singletonList("change status");

    }


}
