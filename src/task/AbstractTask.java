package task;

import service.Status;

public abstract class AbstractTask {

    protected long id;
    protected String name;
    protected Status status;
    protected String description;

    public AbstractTask(String name, Status status, String descriptions) {
        this.name = name;
        this.status = status;
        this.description = descriptions;
    }

    //--------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
//------------------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //----------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTask that)) return false;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
