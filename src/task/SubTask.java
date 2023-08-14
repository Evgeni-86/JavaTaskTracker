package task;

public class SubTask extends AbstractTask {
    private Epic epic;

    public SubTask(Epic epic, String name, Status status, String descriptions) {
        super(name, status, descriptions);
        this.epic = epic;
    }

    public Epic getEpic() {
        return this.epic;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", epicId=" + epic.getId();
    }
}
