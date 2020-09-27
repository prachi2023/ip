package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Integer numTasksDone = 0;
    public TaskList(){

    }
    public TaskList (ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public static void addTodo(String task) throws DukeException {
        if (task.equals("")){
            throw new DukeException("No description of added");
        }
        tasks.add(new ToDo(task));
    }
    public static void addDeadline (String description, LocalDate date, LocalTime time) throws DukeException {
        tasks.add(new Deadline(description, date, time));
    }
    public static void addEvent (String description,  LocalDate date, LocalTime time) throws DukeException {
        tasks.add(new Event(description, date, time));
    }

    public static void markTaskDone (Integer index){
        Task task = tasks.get(index);
        task.markTaskDone();
        numTasksDone ++;
    }

    public static void deleteTask (int index){
        Task task = tasks.get(index);
        //If the task removed has been completed reduce the number of completed tasks by 1
        // so that the list view and the num of tasks done match
        if (task.getIsDone()){
            numTasksDone --;
        }
        tasks.remove(index);
    }

    public static Task get(Integer index){
        return tasks.get(index);
    }

    public static Integer size(){
        return tasks.size();
    }

    public static int getNumTasksDone (){
        return numTasksDone;
    }
}
