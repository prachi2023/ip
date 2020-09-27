package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class HelpCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        ui.printCommandInfo();
    }
}
