package history;

import task.AbstractTask;

import java.util.List;

public interface HistoryManager {
    void addTask(AbstractTask task);
    List<AbstractTask> getHistory();

    void remove(int id);
}
