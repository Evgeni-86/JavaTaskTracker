package task;

import service.Status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Epic extends AbstractTask {

    private List<SubTask> epicSubTaskList = new ArrayList<>();

    public Epic(String name, Status status, String descriptions) {
        super(name, status, descriptions);
    }

    public Epic(String name, Status status, String descriptions, long id) {
        this(name, status, descriptions);
        this.id = id;
    }

    //----------------------------------------
    public List<SubTask> getEpicSubTaskList() {
        return epicSubTaskList;
    }

    public void setEpicSubTaskList(List<SubTask> epicSubTaskList) {
        this.epicSubTaskList = epicSubTaskList;
    }

    public Status updateStatus() {
        HashSet<Status> statuses = new HashSet<>();
        epicSubTaskList.forEach((subTask) -> statuses.add(subTask.getStatus()));

        if (statuses.size() == 2) {
            this.status = Status.IN_PROGRESS;
        } else if (statuses.iterator().hasNext()) {
            this.status = statuses.iterator().next();
        } else {
            this.status = Status.NEW;//ЕСЛИ ПУСТО
        }

        return this.status;
    }
}
