package duke.task;

/**
 * Represents work that has to be done. A <code>Task</code> object corresponds to
 * a task represented using a string for the description of it eg <code> CS2113 tutorial </code>
 * and a Boolean to keep track of whether it has been completed
 * No date and time information is represented
 */
public class ToDo extends Task{
    public ToDo (String description){
        super(description);
    }
    public String toString(){
        return String.format ("[T][%s] %s", getStatusIcon(),this.description);
    }
    public String saveFormat(){
        return String.format("T:%s:%s", isDone, description);
    }
}
