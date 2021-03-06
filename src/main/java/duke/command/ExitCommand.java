package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command that ends the application
 */
public class ExitCommand extends Command{
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        this.isExit = true;
    }
}
