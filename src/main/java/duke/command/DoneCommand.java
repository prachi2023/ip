package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command{
    private Integer taskNum;

    public DoneCommand(Integer taskNum){
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.get(taskNum);
        if (task.getIsDone()){
            ui.printTaskPreviouslyMarkedDone(task.toString());
        }else{
            tasks.markTaskDone(taskNum);
            ui.printTaskMarkedDone(task.toString());
            try {
                storage.editOrDeleteTaskFile(tasks);
            } catch (IOException e){
                ui.showFileEditingError();
            }
        }
    }
}
