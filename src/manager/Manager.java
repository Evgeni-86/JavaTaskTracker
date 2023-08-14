package manager;

import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.HashMap;

public class Manager {
    private final HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private final HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private int id_generator = 1;

    //--ДОБАВЛЕНИЕ ЗАДАЧ---------------------
    public int addTask(Task task) {
        int id = id_generator++;
        task.setId(id);
        taskHashMap.put(id, task);
        if (!taskHashMap.containsKey(id)){ //НУЖНА ЛИ ПРОВЕРКА
            System.out.println("задача не добавлена");
            return -1;
        }
        return id;
    }

    public int addSubTask(SubTask subTask) {
        int id = id_generator++;
        subTask.setId(id);
        subTaskHashMap.put(id, subTask);
        return id;
    }

    public int addEpic(Epic epic) {
        int id = id_generator++;
        epic.setId(id);
        epicHashMap.put(id, epic);
        return id;
    }

    //--ПОЛУЧЕНИЕ СПИСКА ЗАДАЧ----------------
    public HashMap<Integer, Task> getAllTaskHashMap() {
        return taskHashMap;
    }

    public HashMap<Integer, SubTask> getAllSubTaskHashMap() {
        return subTaskHashMap;
    }

    public HashMap<Integer, Epic> getAllEpicHashMap() {
        return epicHashMap;
    }

    //--УДАЛЕНИЕ ВСЕХ ЗАДАЧ-------------------
    public void clearTaskHashMap() {
        taskHashMap.clear();
    }

    public void clearSubTaskHashMap() {
        subTaskHashMap.clear();
    }

    public void clearEpicHashMap() {
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
    public int updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
        return task.getId();
    }

    public int updateEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
        return epic.getId();
    }

    public int updateSubTask(SubTask subTask) {
        subTaskHashMap.put(subTask.getId(), subTask);
        Epic epicThisSubTask = subTask.getEpic();
        epicThisSubTask.updateStatus();
        return subTask.getId();
    }

    //--УДАЛЕНИЕ ПО ИДЕНТИФИКАТОРУ---------------
    public Task removeTask(Task task) {
        return taskHashMap.remove(task.getId());
    }

    public Epic removeEpic(Epic epic) {
        return epicHashMap.remove(epic.getId());
    }

    public SubTask removeSubTask(SubTask subTask) {
        return subTaskHashMap.remove(subTask.getId());
    }

}
