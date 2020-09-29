package duke.task;

import java.time.LocalDate;

/**
 * Represents work that has to be done. A <code>Task</code> object corresponds to
 * a task represented using a string for the description of it eg <code> CS2113 tutorial </code>
 * and a Boolean to keep track of whether it has been completed
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String of the task with the format of "[isDone] description"
     * Method always returns immediately
     *
     * @return task information
     */
    public String toString (){
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a string of the task information
     * Formatted with : between each new piece of information
     * Use this format to save it to storage
     *
     * @return task information
     */
    public String saveFormat(){
        return String.format("%s:%s", isDone, description);
    }
    public void markTaskDone() {
        this.isDone = true;
    }
    public Boolean getIsDone (){
        return this.isDone;
    }

    /**
     * Returns a String of the icon to be shown to the user when printing the task
     * @return status icon
     */
    public String getStatusIcon(){
        return (isDone ? "Done": "X");
    }
    public String getDescription(){
        return this.description;
    }
    // These return 0 as there is no dateTime associated with a basic task
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
