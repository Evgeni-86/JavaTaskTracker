package history;

import task.AbstractTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final HashMap<Long, Node> historyHashMap = new HashMap<>();

    //==LINKED LIST===============================
    private Node head;
    private Node tail;

    public Node linkLast(AbstractTask task) {
        Node newNodeCurrenTask = new Node(task, tail, null);
        Node oldTail = tail;
        tail = newNodeCurrenTask;
        if (oldTail == null) {
            head = newNodeCurrenTask;
        } else {
            oldTail.setNext(tail);
        }
        return newNodeCurrenTask;
    }

    public List<AbstractTask> getTasks() {
        ArrayList<AbstractTask> historyList = new ArrayList<>();
        for (Node tempHead = head; tempHead != null; tempHead = tempHead.getNext()) {
            historyList.add(tempHead.getTask());
        }
        return historyList;
    }

    private void connectElements(Node elementOne, Node elementTwo) {
        if (elementOne != null) {
            elementOne.setNext(elementTwo);
        } else {
            head = elementTwo;
        }

        if (elementTwo != null) {
            elementTwo.setPrev(elementOne);
        } else {
            tail = elementOne;
        }
    }

    private void removeNode(Node node) {
        if (node != null) {
            connectElements(node.getPrev(), node.getNext());
        }
    }
    //==================================================

    @Override
    public void add(AbstractTask task) {
        if (task == null){//используется в методе loadFromFile FileBackedTaskManager
            return;
        }
        remove(task.getId());
        historyHashMap.put(task.getId(), linkLast(task));
    }

    @Override
    public List<AbstractTask> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(long id) {
        removeNode(historyHashMap.remove(id));
    }
}

//------------------------------------------------------
class Node {
    private final AbstractTask task;
    private Node prev;
    private Node next;

    public Node(AbstractTask task, Node prev, Node next) {
        this.task = task;
        this.prev = prev;
        this.next = next;
    }

    public AbstractTask getTask() {
        return task;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }
}