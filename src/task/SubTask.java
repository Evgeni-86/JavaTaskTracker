package task;

public class SubTask extends AbstractTask {
    private final long epicId;

    public SubTask(long epicId, String name, Status status, String descriptions) {
        super(name, status, descriptions);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return this.epicId;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", epicId=" + epicId;
    }
}
