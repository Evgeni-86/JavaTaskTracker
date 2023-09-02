package history;

import task.AbstractTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final HashMap<Long, Node> historyHashMap = new HashMap<>();

    //==CustomLinkedList===============================
    private Node head;
    private Node tail;

    public void linkLast(AbstractTask task) {
        Node newNodeCurrenTask = new Node(task);
        if (head == null) {
            head = newNodeCurrenTask;
        } else {
            connectElements(tail, newNodeCurrenTask);
        }
        tail = newNodeCurrenTask;
        historyHashMap.put(task.getId(), newNodeCurrenTask);
    }

    public ArrayList<AbstractTask> getTasks() {
        ArrayList<AbstractTask> historyList = new ArrayList<>();
        for (Node tempHead = head; tempHead != null ; tempHead = tempHead.getNext()) {
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
        }
    }

    private void removeNode(Node node){
        if (node != null){
            connectElements(node.getPrev(), node.getNext());
        }
    }
    //==================================================

    @Override
    public void add(AbstractTask task) {
        removeNode(historyHashMap.remove(task.getId()));
        linkLast(task);
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

    public Node(AbstractTask task) {
        this.task = task;
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