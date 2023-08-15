package manager;

import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Manager {
    private final HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private final HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private int id_generator = 1;

    //--ДОБАВЛЕНИЕ ЗАДАЧ---------------------
    public int addTask(Task task) {
        if (task == null) {
            return -1;
        }
        int id = id_generator++;
        task.setId(id);
        taskHashMap.put(id, task);
        return id;
    }

    public int addSubTask(SubTask subTask) {
        if (subTask == null || !epicHashMap.containsKey(subTask.getEpic())) {
            return -1;
        }
        int id = id_generator++;
        subTask.setId(id);
        subTaskHashMap.put(id, subTask);
        Epic currentEpic = epicHashMap.get(subTask.getEpic());
        currentEpic.getEpicSubTaskList().add(subTask);
        currentEpic.updateStatus();
        return id;
    }

    public int addEpic(Epic epic) {
        if (epic == null) {
            return -1;
        }
        int id = id_generator++;
        epic.setId(id);
        epicHashMap.put(id, epic);
        return id;
    }

    //--ПОЛУЧЕНИЕ СПИСКА ЗАДАЧ----------------
    public List<Task> getAllTaskHashMap() {
        return new ArrayList<>(taskHashMap.values());
    }

    public List<SubTask> getAllSubTaskHashMap() {
        return new ArrayList<>(subTaskHashMap.values());
    }

    public List<Epic> getAllEpicHashMap() {
        return new ArrayList<>(epicHashMap.values());
    }

    //--УДАЛЕНИЕ ВСЕХ ЗАДАЧ-------------------
    public void clearTaskHashMap() {
        taskHashMap.clear();
    }

    public void clearSubTaskHashMap() {
        subTaskHashMap.clear();
    }

    public void clearEpicHashMap() {
        subTaskHashMap.clear();
        epicHashMap.clear();
    }

    //--ПОЛУЧЕНИЕ ПО ИДЕНТИФИКАТОРУ-------------
    public Task getTaskById(int id) {
        return taskHashMap.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTaskHashMap.get(id);
    }

    public Epic getEpicById(int id) {
        return epicHashMap.get(id);
    }

    //--ОБНОВЛЕНИЕ ЗАДАЧИ------------------------
    public boolean updateTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())) {
            return false;
        }
        taskHashMap.put(task.getId(), task);
        return true;
    }

    public boolean updateEpic(Epic epic) {
        if (epic == null || !epicHashMap.containsKey(epic.getId())) {
            return false;
        }
        epicHashMap.put(epic.getId(), epic);
        return true;
    }

    public boolean updateSubTask(SubTask subTask) {
        if (subTask == null || !subTaskHashMap.containsKey(subTask.getId()) || !epicHashMap.containsKey(subTask.getEpic())) {
            return false;
        }
        subTaskHashMap.put(subTask.getId(), subTask);
        Epic epicThisSubTask = epicHashMap.get(subTask.getEpic());
        epicThisSubTask.getEpicSubTaskList().//????
        epicThisSubTask.updateStatus();
        return true;
    }

    //--УДАЛЕНИЕ ПО ИДЕНТИФИКАТОРУ---------------
    public Task removeTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())){
            return null;
        }
        return taskHashMap.remove(task.getId());
    }

    public Epic removeEpic(Epic epic) {
        if (epic == null || !epicHashMap.containsKey(epic.getId())){
            return null;
        }

        for (SubTask subtask : epic.getEpicSubTaskList()) {
            subTaskHashMap.remove(subtask.getId());
        }
        return epicHashMap.remove(epic.getId());
    }

    public SubTask removeSubTask(SubTask subTask) {
        Epic epicThisSubTask = subTask.getEpic();
        epicThisSubTask.getEpicSubTaskList().remove(subTask);
        epicThisSubTask.updateStatus();
        return subTaskHashMap.remove(subTask.getId());
    }

}
