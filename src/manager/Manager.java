package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private final HashMap<Long, Task> taskHashMap = new HashMap<>();
    private final HashMap<Long, Epic> epicHashMap = new HashMap<>();
    private final HashMap<Long, SubTask> subTaskHashMap = new HashMap<>();
    private long id_generator = 1;

    //--ДОБАВЛЕНИЕ ЗАДАЧ---------------------
    public long addTask(Task task) {
        if (task == null) {
            return -1;
        }
        long id = id_generator++;
        task.setId(id);
        taskHashMap.put(id, task);
        return id;
    }

    public long addEpic(Epic epic) {
        if (epic == null) {
            return -1;
        }
        long id = id_generator++;
        epic.setId(id);
        epicHashMap.put(id, epic);
        return id;
    }

    public long addSubTask(SubTask subTask) {
        if (subTask == null || !epicHashMap.containsKey(subTask.getEpicId())) {
            return -1;
        }
        long id = id_generator++;
        subTask.setId(id);
        subTaskHashMap.put(id, subTask);
        Epic currentEpic = epicHashMap.get(subTask.getEpicId());
        currentEpic.getEpicSubTaskList().add(subTask);
        currentEpic.updateStatus();
        return id;
    }

    //--ПОЛУЧЕНИЕ СПИСКА ЗАДАЧ----------------
    public List<Task> getAllTaskHashMap() {
        return new ArrayList<>(taskHashMap.values());
    }

    public List<Epic> getAllEpicHashMap() {
        return new ArrayList<>(epicHashMap.values());
    }

    public List<SubTask> getAllSubTaskHashMap() {
        return new ArrayList<>(subTaskHashMap.values());
    }

    //--УДАЛЕНИЕ ВСЕХ ЗАДАЧ-------------------
    public void clearTaskHashMap() {
        taskHashMap.clear();
    }

    public void clearEpicHashMap() {
        subTaskHashMap.clear();
        epicHashMap.clear();
    }

    public void clearSubTaskHashMap() {
        subTaskHashMap.clear();
    }

    //--ПОЛУЧЕНИЕ ПО ИДЕНТИФИКАТОРУ-------------
    public Task getTaskById(long id) {
        return taskHashMap.get(id);
    }

    public Epic getEpicById(long id) {
        return epicHashMap.get(id);
    }

    public SubTask getSubTaskById(long id) {
        return subTaskHashMap.get(id);
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
        //----subtask из старого епика в обновленный-----
        Epic oldEpic = getEpicById(epic.getId());
        epic.setEpicSubTaskList(oldEpic.getEpicSubTaskList());
        //-----------------------------------------------
        epicHashMap.put(epic.getId(), epic);
        return true;
    }

    public boolean updateSubTask(SubTask subTask) {
        if (subTask == null || !subTaskHashMap.containsKey(subTask.getId()) || !epicHashMap.containsKey(subTask.getEpicId())) {
            return false;
        }
        subTaskHashMap.put(subTask.getId(), subTask);
        Epic epicThisSubTask = epicHashMap.get(subTask.getEpicId());
        epicThisSubTask.getEpicSubTaskList().remove(subTask);//УДАЛИТЬ СТАРУЮ ПОДЗАДАЧУ
        epicThisSubTask.getEpicSubTaskList().add(subTask);//ДОБАВИТЬ ОБНОВЛЕННУЮ
        epicThisSubTask.updateStatus();
        return true;
    }

    //--УДАЛЕНИЕ ПО ИДЕНТИФИКАТОРУ---------------
    public Task removeTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())) {
            return null;
        }
        return taskHashMap.remove(task.getId());
    }

    public Epic removeEpic(Epic epic) {
        if (epic == null || !epicHashMap.containsKey(epic.getId())) {
            return null;
        }
//        for (SubTask subtask : epic.getEpicSubTaskList()) {
//            subTaskHashMap.remove(subtask.getId());
//        }
        epic.getEpicSubTaskList().forEach(elem -> subTaskHashMap.remove(elem.getId()));
        return epicHashMap.remove(epic.getId());
    }

    public SubTask removeSubTask(SubTask subTask) {
        if (subTask == null || !subTaskHashMap.containsKey(subTask.getId()) || !epicHashMap.containsKey(subTask.getEpicId())) {
            return null;
        }
        Epic epicThisSubTask = epicHashMap.get(subTask.getEpicId());
        epicThisSubTask.getEpicSubTaskList().remove(subTask);
        epicThisSubTask.updateStatus();
        return subTaskHashMap.remove(subTask.getId());
    }
}
