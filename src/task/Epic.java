package task;

import java.util.ArrayList;
import java.util.HashSet;

public class Epic extends AbstractTask {

    private final ArrayList<SubTask> epicSubTaskList = new ArrayList<>();

    public Epic(String name, Status status, String descriptions) {
        super(name, status, descriptions);
    }

    //----------------------------------------
    public SubTask addSubTaskInEpic(SubTask subTask) {
        epicSubTaskList.add(subTask);
        return subTask;
    }

    public ArrayList<SubTask> getEpicSubTaskList() {
        return epicSubTaskList;
    }

    public Status updateStatus() {
        if (epicSubTaskList.size() == 0){
            this.status = Status.NEW;
            return this.status;
        }

        HashSet<Status> statuses = new HashSet<>();
        for (SubTask subTask : epicSubTaskList) {
            statuses.add(subTask.getStatus());
        }

        if (statuses.contains(Status.NEW) && statuses.contains(Status.DONE)) {
            this.status = Status.IN_PROGRESS;
        } else if (statuses.contains(Status.NEW)) {
            this.status = Status.NEW;
        } else if (statuses.contains((Status.DONE))) {
            this.status = Status.DONE;
        }
        return this.status;
    }
}