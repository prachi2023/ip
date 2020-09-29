package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {
    private char taskType;
    private String taskDescription;
    private LocalDate date;
    private LocalTime time;

    public AddCommand (char taskType, String taskDescription){
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }
    public AddCommand (char taskType, String taskDescription, LocalDate date, LocalTime time){
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.date = date;
        this.time = time;
    }
    @Override
    public void execute (Ui ui, TaskList tasks, Storage storage) throws DukeException{
        switch(taskType){
            case 'T':
                tasks.addTodo(taskDescription);
                break;
            case 'D':
                tasks.addDeadline(taskDescription, date, time);
                break;
            case 'E':
                tasks.addEvent(taskDescription, date, time);
                break;
            default:
                throw new DukeException("Parser error");
        }
        ui.printAddedTask(tasks.get(tasks.size()-1).toString());
        try{
            storage.addTaskToFile (tasks.size()-1,tasks);
        } catch (IOException e){
            ui.showFileEditingError();
        }
    }

}
