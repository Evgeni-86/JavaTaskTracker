package task;

import java.util.ArrayList;
import java.util.HashSet;

public class Epic extends AbstractTask {

    private final ArrayList<SubTask> epicSubTaskList = new ArrayList<>();

    public Epic(String name, Status status, String descriptions) {
        super(name, status, descriptions);
    }

    //----------------------------------------
    public ArrayList<SubTask> getEpicSubTaskList() {
        return epicSubTaskList;
    }

    public Status updateStatus() {
        HashSet<Status> statuses = new HashSet<>();
        epicSubTaskList.forEach((subTask) -> statuses.add(subTask.getStatus()));

//        for (SubTask subTask : epicSubTaskList) {
//            statuses.add(subTask.getStatus());
//        }

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
