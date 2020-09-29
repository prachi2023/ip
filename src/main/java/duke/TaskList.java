package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents the array of tasks that are stored
 * Deals with the addition, deletion and modification of the tasks
 */
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

    /**
     * Changes the status of the task from not done to done
     * Index needs to be within 0 and the size of the tasklist
     *
     * @param index index of task in the tasklist to be marked as completed
     * @exception DukeException throws an exception for the index out of range
     */
    public static void markTaskDone (Integer index) throws DukeException{
        if (index < 0 || index >= tasks.size()){
            throw new DukeException("Invalid task number");
        }
        Task task = tasks.get(index);
        task.markTaskDone();
    }

    /**
     * Deletes a task from the tasklist
     * Index needs to be within 0 and the size of the tasklist
     * Task deleted is the task with the index entered in the tasklist
     *
     * @param index index of the task in the tasklist
     */
    public static void deleteTask (int index){
        tasks.remove(index);
    }

    /**
     * Returns a boolean.
     * Checks if the task description has the keywords inputted by the user
     * Index needs to be within 0 and the TaskList size
     *
     * @param index index of the task in the tasklist
     * @param keyword string which is the keyword/s
     * @return The status of the task containing the keyword
     * @exception DukeException throws an exception for the index out of range
     */
    public static Boolean checkTask(int index, String keyword) throws DukeException{
        if (index < 0 || index >= tasks.size()){
            throw new DukeException("Invalid task number");
        }
        Task task = tasks.get(index);
        if (task.getDescription().contains(keyword)){
            return true;
        }
        return false;
    }

    /**
     * Returns a Task object in the tasklist with the corresponding index.
     * index needs to be within 0 and the size of the tasklist
     *
     * @param index of the task to be extracted from the tasklist
     * @return task
     * @exception DukeException throws an exception for the index out of range
     */
    public static Task get(Integer index) throws DukeException{
        if (index < 0 || index >= tasks.size()){
            throw new DukeException("Invalid task number");
        }
        return tasks.get(index);
    }

    /**
     *
     * @return Number of tasks in the tasklist
     */
    public static Integer size(){
        return tasks.size();
    }
}
