package manager;

import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.HashMap;

public class Manager {
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicHashMap = new HashMap<>();


    int id_generator;

    public int addTask(Task task) {
        int id = id_generator++;
        task.setId(id);
        taskHashMap.put(id, task);
        return id;
    }

    public int addSubTask(SubTask task) {
        int id = id_generator++;
        task.setId(id);
        subTaskHashMap.put(id, task);
        return id;
    }

    public int addEpic(Epic epic) {
        int id = id_generator++;
        epic.setId(id);
        epicHashMap.put(id, epic);
        return id;
    }

}
