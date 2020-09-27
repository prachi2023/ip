package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand (int index){
        this.index = index;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.size()){
            throw new DukeException ("Invalid task number");
        }
        ui.printDeletedTask(tasks.get(index).toString());
        tasks.deleteTask(this.index);
        try{
            storage.editOrDeleteTaskFile(tasks);
        } catch (IOException e){
            ui.showFileEditingError();
        }
    }

}
