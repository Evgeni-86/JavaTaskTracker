package manager;

import history.HistoryManager;
import service.Managers;
import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    protected final HashMap<Long, Task> taskHashMap = new HashMap<>();
    protected final HashMap<Long, Epic> epicHashMap = new HashMap<>();
    protected final HashMap<Long, SubTask> subTaskHashMap = new HashMap<>();
    private long id_generator = 1;

    protected final HistoryManager historyManager = Managers.getDefaultHistory();

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
        taskHashMap.keySet().forEach(elem -> historyManager.remove(elem));
        taskHashMap.clear();
    }

    @Override
    public void clearEpicHashMap() {
        subTaskHashMap.keySet().forEach(elem -> historyManager.remove(elem));
        epicHashMap.keySet().forEach(elem -> historyManager.remove(elem));
        subTaskHashMap.clear();
        epicHashMap.clear();
    }

    @Override
    public void clearSubTaskHashMap() {
        subTaskHashMap.keySet().forEach(elem -> historyManager.remove(elem));
        epicHashMap.values().forEach(elem -> {
            elem.getEpicSubTaskList().clear();
            elem.updateStatus();
        });
        subTaskHashMap.clear();
    }

    //--ПОЛУЧЕНИЕ ПО ИДЕНТИФИКАТОРУ-------------
    @Override
    public Task getTaskById(long id) {
        if (id == 0 || !taskHashMap.containsKey(id)) {
            return null;
        }
        Task currentTask = taskHashMap.get(id);
        historyManager.add(currentTask);
        return currentTask;
    }

    @Override
    public Epic getEpicById(long id) {
        if (id == 0 || !epicHashMap.containsKey(id)) {
            return null;
        }
        Epic currentEpic = epicHashMap.get(id);
        historyManager.add(currentEpic);
        return currentEpic;
    }

    @Override
    public SubTask getSubTaskById(long id) {
        if (id == 0 || !subTaskHashMap.containsKey(id)) {
            return null;
        }
        SubTask currentSubTask = subTaskHashMap.get(id);
        historyManager.add(currentSubTask);
        return currentSubTask;
    }

    //--ОБНОВЛЕНИЕ ЗАДАЧИ------------------------
    @Override
    public boolean updateTask(Task task) {
        if (task == null || !taskHashMap.containsKey(task.getId())) {
            return false;
        }
        taskHashMap.put(task.getId(), task);
        historyManager.add(task);
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
        historyManager.add(epic);
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
        historyManager.add(subTask);
        return true;
    }

    //--УДАЛЕНИЕ ПО ИДЕНТИФИКАТОРУ---------------
    @Override
    public Task removeTask(long id) {
        if (id == 0 || !taskHashMap.containsKey(id)) {
            return null;
        }
        historyManager.remove(id);
        return taskHashMap.remove(id);
    }

    @Override
    public Epic removeEpic(long id) {
        if (id == 0 || !epicHashMap.containsKey(id)) {
            return null;
        }
        epicHashMap.get(id).getEpicSubTaskList().forEach(elem -> {
            historyManager.remove(elem.getId());
            subTaskHashMap.remove(elem.getId());
        });
        historyManager.remove(id);
        return epicHashMap.remove(id);
    }

    @Override
    public SubTask removeSubTask(long id) {
        SubTask currenSubTask = subTaskHashMap.get(id);
        if (id == 0 || currenSubTask == null || !epicHashMap.containsKey(currenSubTask.getEpicId())) {
            return null;
        }
        Epic epicThisSubTask = epicHashMap.get(currenSubTask.getEpicId());
        epicThisSubTask.getEpicSubTaskList().remove(currenSubTask);
        epicThisSubTask.updateStatus();
        historyManager.remove(currenSubTask.getId());
        return subTaskHashMap.remove(currenSubTask.getId());
    }
}
