package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class AddCommand extends Command {
    private char taskType;
    private String taskDescription;
    private String taskTime;

    public AddCommand (char taskType, String taskDescription){
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }
    public AddCommand (char taskType, String taskDescription, String time){
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskTime = time;
    }
    @Override
    public void execute (Ui ui, TaskList tasks, Storage storage) throws DukeException{
        switch(taskType){
            case 'T':
                tasks.addTodo(taskDescription);
                break;
            case 'D':
                tasks.addDeadline(taskDescription, taskTime);
                break;
            case 'E':
                tasks.addEvent(taskDescription, taskTime);
                break;
            default:
                throw new DukeException("Parser error");
        }
        ui.printAddedTask(tasks.get(tasks.size()-1).toString());
    }

}
