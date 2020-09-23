package duke.task;

public class Event extends Task{
    private String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public String toString (){
        return String.format("[E][%s] %s(at:%s)", getStatusIcon(), description, at);
    }
    public String saveFormat(){
        return String.format("E:%s:%s:%s", isDone, description, at);
    }
}
