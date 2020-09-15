package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static boolean shouldExit = false;
    public static int numOfTasks = 0;
    public static Task[] tasks = new Task[10];
    public static String path = "data/tasklist.txt";

    //Check and create file
    public static void setUpFile() throws IOException{
        File f = new File(path);
        if (!f.exists()){
            f.createNewFile();
        }else {
            try{
                loadData();
            }catch (FileNotFoundException e){
                //should not occur since, it has been checked that there is a file
                System.out.println("File not found");
            }
        }
    }
    //Load data from file
    public static void loadData() throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        // get the tasks line by line
        while (s.hasNext()){
            // | is the barrier between the information
            String taskInput = s.nextLine();
            String[] task = taskInput.split(":");

            System.out.println("");
            switch (task[0]) {
            case "T":
                tasks[numOfTasks] = new ToDo(task[2]);
                break;
            case "D":
                tasks[numOfTasks] = new Deadline(task[2], task[3]);
                break;
            case "E":
                tasks[numOfTasks] = new Event(task[2], task[3]);
                break;
            default:
                tasks[numOfTasks] = new Task(task[2]);
            }
            if (task[1] == "true"){
                tasks[numOfTasks].markTaskDone();
            }
            numOfTasks ++;
        }
    }

    //Add a task to the file
    public static void addTaskToFile (int index) throws IOException{
        FileWriter fw = new FileWriter(path, true);
        String taskToWrite = tasks[index].saveFormat();
        fw.write(taskToWrite + System.lineSeparator());
        fw.close();
    }
    //edit the details of the task in the file
    public static void editTaskInFile (int index) throws IOException{

    }

    public static void exitDuke (){
        shouldExit = true;
        System.out.println("Bye. Hope to see you again soon!");
    }
    // Print the list of tasks entered
    public static void printList (){
        System.out.println("Here is your List of Tasks: ");
        for (int i = 0; i < numOfTasks; i++){
            System.out.println(i+1 + ". " + tasks[i]);
        }
        System.out.println("Now you have " + numOfTasks + " Tasks in the list");
    }

    // Mark a specific task in the list as done
    public static void markTaskInListAsDone(int taskNumCompleted){

            if (taskNumCompleted > numOfTasks) {
                System.out.println("This is not a valid task\nPlease enter a valid task number");
            }
            if (tasks[taskNumCompleted - 1].getIsDone()){
                System.out.println("This task has already been marked as done!");
            }else{
                System.out.println("Good Job completing your task! I have marked it as done.");
                tasks[taskNumCompleted - 1].markTaskDone();
            }
            System.out.println(tasks[taskNumCompleted - 1]);
    }

    // Add the specific type of tasks to the big list of tasks
    public static void addTodo  (String task) throws DukeException{
        if (task.equals("")){
            throw new DukeException("no description");
        }
        tasks[numOfTasks] = new ToDo(task);
        System.out.println("There you go I've added it to the list\n" + tasks[numOfTasks]);
        numOfTasks ++;
    }

    public static void addDeadline (String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/by", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException("no description");
        }
        if (!task.contains("/by")){
            throw new DukeException("No /by");
        }
        tasks[numOfTasks] = new Deadline(taskDetails[0].trim(), taskDetails[1].trim());
        System.out.println("There you go I've added it to the list\n" + tasks[numOfTasks]);
        numOfTasks ++;
    }

    public static void addEvent (String task) throws DukeException{
        //index 0 refers to the description of the task and index 1 refers to the deadline
        String[] taskDetails = task.split("/at", 2);
        if (taskDetails[0].isEmpty()){
            throw new DukeException("no description");
        }
        if (!task.contains("/at")){
            throw new DukeException("No /at");
        }
        tasks[numOfTasks] = new Event(taskDetails[0].trim(), taskDetails[1].trim());
        System.out.println("There you go I've added it to the list\n" + tasks[numOfTasks]);
        numOfTasks ++;
    }


    // Determine the command sent and execute accordingly
    public static void executeCommand (String input){
        // Find the command word entered
        String[] userInput = input.split(" ",2);
        String command = userInput[0];
        // Determine the command
        switch (command) {
        case "bye":
            exitDuke();
            break;
        case "list":
            printList();
            break;
        case "done":
            try {
                int taskNumCompleted = Integer.parseInt(userInput[1].trim());
                markTaskInListAsDone(taskNumCompleted);
                editTaskInFile(taskNumCompleted);
            } catch (NumberFormatException e) {
                System.out.println("Task index is not a number: please enter a valid integer");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "todo":
            try{
                addTodo(userInput[1]);
                addTaskToFile (numOfTasks - 1);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println ("Oopsies, seems like you did not enter a description for the task. A todo needs a description!");
            } catch (DukeException e){
                System.out.println ("Oopsies, seems like you left the description blank. A todo needs a description!");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "deadline":
            try{
                addDeadline(userInput[1]);
                addTaskToFile (numOfTasks - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. A deadline needs a description!");
            } catch (DukeException e){
                System.out.println("A deadline requires the time it is due");
            } catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        case "event":
            try{
                addEvent(userInput[1]);
                addTaskToFile (numOfTasks - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. An event needs a description!");
            } catch (DukeException e){
                System.out.println("An event requires the time of the event");
            }catch (IOException e){
                System.out.println ("File is unable to be edited");
            }
            break;
        // If it is none of the above commands, Tell the user to enter a valid command
        default:
            System.out.println ("Please enter a valid command! I do not understand '" + userInput[0] + "'");
        }
    }

    public static void main(String[] args) {
        // set up scanner
        Scanner in = new Scanner (System.in);
        String command;

        // Introduction
        System.out.println ("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        try{
            setUpFile();
        }catch (IOException e){
            System.out.println ("Unable to create a file name 'tasklist'");
        }
        while (!shouldExit){
            command = in.nextLine();
            executeCommand(command);
        }
    }
}
