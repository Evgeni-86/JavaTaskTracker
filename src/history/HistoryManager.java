package history;

import task.AbstractTask;

import java.util.List;

public interface HistoryManager {
    void add(AbstractTask task);
    List<AbstractTask> getHistory();
    void remove(long id);
}
