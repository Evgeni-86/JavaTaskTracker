package task;

public abstract class AbstractTask {

    protected int id;

    protected String name;

    protected Status status;

    protected String descriptions;

    public AbstractTask(int id, String name, Status status, String descriptions) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.descriptions = descriptions;
    }
//--------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescriptions() {
        return descriptions;
    }
//------------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
