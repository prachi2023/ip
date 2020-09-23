package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        ui.printTaskListInfo(tasks.size(), tasks.getNumTasksDone());
        for (int i = 0; i < tasks.size(); i++){
            ui.printTask(i+1, tasks.get(i).toString());
        }
    }
}
