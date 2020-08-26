public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markTaskDone() {
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713": "\u2718");
    }
    public void printTaskDetails (){
        System.out.println("["+ this.getStatusIcon() + "] " + this.description);
    }
}
