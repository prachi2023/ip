package duke;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Represents the file that stores the data. A <code>Storage</code> object corresponds to
 * a filepath where the file is stored
 * a scanner <code>s </code> to read the file data
 * and a file <code>f </code> which stores the data
 */
public class Storage {
    private String filePath;
    File f;
    Scanner s;

    public Storage (String filePath) throws IOException {
        this.filePath = filePath;
        f = new File(filePath);
        if (!f.exists()){
            f.createNewFile();
        }
        s = new Scanner(f);

    }

    /**
     * Reads the tasks from the file, if there is a file that already existed
     * @param tasks Tasklist where the tasks are stored
     * @param parser Parser to parse the information into the relevant Object types
     * @throws DukeException
     */
    public void load (TaskList tasks, Parser parser) throws DukeException{
        while (s.hasNext()){
            String taskInput = s.nextLine();
            String[] task = taskInput.split(":", 5);
            LocalDate date;
            LocalTime time;
            switch (task[0]) {
                case "T":
                    tasks.addTodo(task[2]);
                    break;
                case "D":
                    date = parser.parseDate(task[3]);
                    time = LocalTime.parse(task[4]);
                    tasks.addDeadline(task[2], date, time);
                    break;
                case "E":
                    date = parser.parseDate(task[3]);
                    time = LocalTime.parse(task[4]);
                    tasks.addEvent(task[2], date, time);
                    break;
                default:
                    throw new DukeException("File format error");
            }
            if (task[1].equals("true")){
                tasks.markTaskDone(tasks.size()-1);
            }
        }
    }

    /**
     * Writes to the file to add a line about the new Task that has been added to the tasklist
     * @param index index of the task in the tasklist to be added
     * @param tasks Tasklist of tasks
     * @throws IOException
     */
    public void addTaskToFile (int index, TaskList tasks) throws IOException, DukeException {
        FileWriter fwAppend = new FileWriter(this.filePath, true);
        String taskToWrite = tasks.get(index).saveFormat();
        fwAppend.write(taskToWrite + System.lineSeparator());
        fwAppend.close();
    }
    /**
     * Rewrites every task in the tasklist to the file
     * @param tasks Tasklist of tasks
     * @throws IOException
     */
    public void editOrDeleteTaskFile (TaskList tasks) throws IOException, DukeException {
        // Delete all the data in the original file
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        //Add back all the data excluding deleted data, including the edited data
        for (int i = 0; i < tasks.size(); i++){
            addTaskToFile(i, tasks);
        }
    }
}
