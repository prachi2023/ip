package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

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
    public String saveFormat(){
        return String.format("%s:%s", isDone, description);
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
    public int getDay(){
        return 0;
    }
    public int getYear(){
        return 0;
    }
    public int getMonth(){
        return 0;
    }
    public LocalDate getDate(){
        return LocalDate.parse("0000:00:00");
    }
}
