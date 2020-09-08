package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String toString (){
        return String.format("[%s] %s", getStatusIcon(), description);
    }
    public void markTaskDone() {
        this.isDone = true;
    }
    public Boolean getIsDone (){
        return this.isDone;
    }
    public String getStatusIcon(){
        return (isDone ? "\u2713": "\u2718");
    }
    public void printTaskDetails (){
        System.out.println("["+ this.getStatusIcon() + "] " + this.description);
    }
}
