package task;

import service.Status;

public class Task extends AbstractTask {

    public Task(String name, Status status, String descriptions) {
        super(name, status, descriptions);
    }

    public Task(String name, Status status, String descriptions, long id) {
        this(name, status, descriptions);
        this.id = id;
    }
}
