package duke.task;
public class ToDo extends Task{
    public ToDo (String description){
        super(description);
    }
    public String toString(){
        return String.format ("[T][%s] %s", getStatusIcon(),this.description);
    }
    //This extracts the information in an easy way to retrieve it from the file
    public String saveFormat(){
        return String.format("T:%s:%s", isDone, description);
    }
}
