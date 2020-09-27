package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(){ }

    public static void addTodo(String task) {
        tasks.add(new ToDo(task));
    }
    public static void addDeadline (String description, LocalDate date, LocalTime time) {
        tasks.add(new Deadline(description, date, time));
    }
    public static void addEvent (String description,  LocalDate date, LocalTime time) {
        tasks.add(new Event(description, date, time));
    }

    public static void markTaskDone (Integer index){
        Task task = tasks.get(index);
        task.markTaskDone();
    }

    public static void deleteTask (int index){
        tasks.remove(index);
    }

    public static Task get(Integer index){
        return tasks.get(index);
    }

    public static Integer size(){
        return tasks.size();
    }
}
