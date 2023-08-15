package task;

public class SubTask extends AbstractTask {
    private final int epicId;

    public SubTask(int epicId, String name, Status status, String descriptions) {
        super(name, status, descriptions);
        this.epicId = epicId;
    }

    public int getEpic() {
        return this.epicId;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", epicId=" + epicId;
    }
}
