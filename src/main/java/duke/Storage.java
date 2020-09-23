package duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void load (TaskList tasks) throws DukeException{
        while (s.hasNext()){
            String taskInput = s.nextLine();
            String[] task = taskInput.split(":");
            switch (task[0]) {
                case "T":
                    tasks.addTodo(task[2]);
                    break;
                case "D":
                    tasks.addDeadline(task[2], task[3]);
                    break;
                case "E":
                    tasks.addEvent(task[2], task[3]);
                    break;
                default:
                    throw new DukeException("File format error");
            }
            if (task[1].equals("true")){
                tasks.markTaskDone(tasks.size()-1);
            }
        }
    }
    public void addTaskToFile (int index, TaskList tasks) throws IOException{
        FileWriter fwAppend = new FileWriter(this.filePath, true);
        String taskToWrite = tasks.get(index).saveFormat();
        fwAppend.write(taskToWrite + System.lineSeparator());
        fwAppend.close();
    }

    public void editOrDeleteTaskFile (TaskList tasks) throws IOException{
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
