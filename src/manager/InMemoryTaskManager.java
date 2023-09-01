package manager;

import history.HistoryManager;
import history.InMemoryHistoryManager;
import service.Managers;
import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager{
    private final HashMap<Long, Task> taskHashMap = new HashMap<>();
    private final HashMap<Long, Epic> epicHashMap = new HashMap<>();
    private final HashMap<Long, SubTask> subTaskHashMap = new HashMap<>();
    private long id_generator = 1;

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<AbstractTask> getHistory() {
        return historyManager.getHistory();
    }

    //--ДОБАВЛЕНИЕ ЗАДАЧ---------------------
    @Override
    public long addTask(Task task) {
        if (task == null) {
            return -1;
        }
        long id = id_generator++;
        task.setId(id);
        taskHashMap.put(id, task);
        return id;
    }
    @Override
    public long addEpic(Epic epic) {
        if (epic == null) {
            return -1;
        }
        long id = id_generator++;
        epic.setId(id);
        epicHashMap.put(id, epic);
        return id;
    }
    @Override
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
    @Override
    public List<Task> getAllTaskHashMap() {
        return new ArrayList<>(taskHashMap.values());
    }
    @Override
    public List<Epic> getAllEpicHashMap() {
        return new ArrayList<>(epicHashMap.values());
    }
    @Override
    public List<SubTask> getAllSubTaskHashMap() {
        return new ArrayList<>(subTaskHashMap.values());
    }

    //--УДАЛЕНИЕ ВСЕХ ЗАДАЧ-------------------
    @Override
    public void clearTaskHashMap() {
        taskHashMap.clear();
    }
    @Override
    public void clearEpicHashMap() {
        subTaskHashMap.clear();
        epicHashMap.clear();
    }
    @Override
    public void clearSubTaskHashMap() {
        subTaskHashMap.clear();
    }

    //--ПОЛУЧЕНИЕ ПО ИДЕНТИФИКАТОРУ-------------
    @Override
    public Task getTaskById(long id) {
        Task currentTask = taskHashMap.get(id);
        historyManager.addTask(currentTask);
        return currentTask;
    }
    @Override
    public Epic getEpicById(long id) {
        Epic currentEpic = epicHashMap.get(id);
        historyManager.addTask(currentEpic);
        return currentEpic;
    }
    @Override
    public SubTask getSubTaskById(long id) {
        SubTask currentSubTask = subTaskHashMap.get(id);
        historyManager.addTask(currentSubTask);
        return currentSubTask;
    }

    //--ОБНОВЛЕНИЕ ЗАДАЧИ------------------------
    @Override
    public boolean updateTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())) {
            return false;
        }
        taskHashMap.put(task.getId(), task);
        return true;
    }
    @Override
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
    @Override
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
    @Override
    public Task removeTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())) {
            return null;
        }
        return taskHashMap.remove(task.getId());
    }
    @Override
    public Epic removeEpic(Epic epic) {
        if (epic == null || !epicHashMap.containsKey(epic.getId())) {
            return null;
        }
        epic.getEpicSubTaskList().forEach(elem -> subTaskHashMap.remove(elem.getId()));
        return epicHashMap.remove(epic.getId());
    }
    @Override
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
