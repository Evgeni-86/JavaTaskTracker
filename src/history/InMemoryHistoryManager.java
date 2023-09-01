package history;

import task.AbstractTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<AbstractTask> history = new ArrayList<>();
    private HashMap<Long, Node> nodeTaskHashMap = new HashMap<>();

    @Override
    public void addTask(AbstractTask task) {

        if (history.size() >= 10) {
            history.remove(0);
        }
        history.add(task);
    }

    @Override
    public List<AbstractTask> getHistory() {
        return history;
    }

    @Override
    public void remove(int id) {

    }
}

class Node{
    private AbstractTask tast;
    private Node prev;
    private Node next;

    public void setTast(AbstractTask tast) {
        this.tast = tast;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}