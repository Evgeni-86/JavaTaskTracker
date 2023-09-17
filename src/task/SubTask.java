package task;

import service.Status;

public class SubTask extends AbstractTask {

    private final long epicId;

    public SubTask(long epicId, String name, Status status, String descriptions) {
        super(name, status, descriptions);
        this.epicId = epicId;
    }

    public SubTask(long epicId, String name, Status status, String descriptions, long id) {
        this(epicId, name, status, descriptions);
        this.id = id;
    }


    public long getEpicId() {
        return this.epicId;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}
