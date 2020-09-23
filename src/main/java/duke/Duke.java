package duke;
import duke.command.Command;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static boolean isExit = false;
    public static TaskList tasks = new TaskList();
    public static String path = "tasklist.txt";
    public static Ui ui;
    public static Storage storage;

    public static void main(String[] args) {
        // set up scanner
        ui = new Ui();
        ui.printWelcomeMessage();
        try{
            storage = new Storage(path);
            storage.load(tasks);
        }catch (IOException e){
            ui.showFileCreatingError(path);
        }catch (DukeException e){
            ui.showErrorMessage(e.getErrorMessage());
        }

/*        try{
            setUpFile();
        }catch (IOException e){
            ui.showFileCreatingError(path);
        } */
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseInput(fullCommand, ui);
                c.execute(ui, tasks, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showErrorMessage(e.getErrorMessage());
            }
        }
    }
}


