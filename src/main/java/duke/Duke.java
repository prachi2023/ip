package duke;
import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;


public class Duke {
    public static boolean isExit = false;
    public static String path = "tasklist.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke (String filepath ){
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();

        // Set up and retrieve data from file
        try{
            storage = new Storage(path);
            storage.load(tasks, parser);
        }catch (IOException e){
            ui.showFileCreatingError(path);
        }catch (DukeException e){
            ui.showErrorMessage(e.getErrorMessage());
        }
    }


    public void run(){
        ui.printWelcomeMessage();

        while (!isExit) {
            try {
                ui.printLine();
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand, ui);
                c.execute(ui, tasks, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showErrorMessage(e.getErrorMessage());
            }
        }
        ui.printExitMessage();
    }
    public static void main(String[] args) {
        new Duke(path).run();
    }
}


