package manager;

import task.AbstractTask;
import task.Epic;
import task.SubTask;
import task.Task;
import java.util.List;

public interface TaskManager {
    long addTask(Task task);
    public long addEpic(Epic epic);
    public long addSubTask(SubTask subTask);

    public List<Task> getAllTaskHashMap();
    public List<Epic> getAllEpicHashMap();
    public List<SubTask> getAllSubTaskHashMap();

    public void clearTaskHashMap();
    public void clearEpicHashMap();
    public void clearSubTaskHashMap();

    public Task getTaskById(long id);
    public Epic getEpicById(long id);
    public SubTask getSubTaskById(long id);

    public boolean updateTask(Task task);
    public boolean updateEpic(Epic epic);
    public boolean updateSubTask(SubTask subTask);

    public Task removeTask(long id);
    public Epic removeEpic(long id);
    public SubTask removeSubTask(long id);
    List<AbstractTask> getHistory();
}
