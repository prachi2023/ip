package duke;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.Scanner;

public class Duke {
    public static boolean shouldExit = false;
    public static int numOfTasks = 0;
    public static Task[] tasks = new Task[10];

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
    public static void markTaskInListAsDone(String index){
            int taskNumCompleted = Integer.parseInt(index.trim());

            if (taskNumCompleted > numOfTasks) {
                System.out.println("This is not a valid task\nPlease enter a valid task number");
            }
            if (tasks[taskNumCompleted - 1].isDone){
                System.out.println("This task has already been marked as done!");
            }else{
                System.out.println("Good Job completing your task! I have marked it as done.");
                tasks[taskNumCompleted - 1].markTaskDone();
            }
            System.out.println(tasks[taskNumCompleted - 1]);
    }

    // Add a ToDo Task
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
                markTaskInListAsDone(userInput[1]);
            } catch (NumberFormatException e) {
                System.out.println("Task index is not a number: please enter a valid integer");
            }
            break;
        case "todo":
            try{
                addTodo(userInput[1]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println ("Oopsies, seems like you did not enter a description for the task. A todo needs a description!");
            } catch (DukeException e){
                System.out.println ("Oopsies, seems like you left the description blank. A todo needs a description!");
            }
            break;
        case "deadline":
            try{
            addDeadline(userInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. A deadline needs a description!");
            } catch (DukeException e){
                System.out.println("A deadline requires the time it is due");
            }
            break;
        case "event":
            try{
                addEvent(userInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oopsies, seems like you did not enter a description for the task. An event needs a description!");
            } catch (DukeException e){
                System.out.println("An event requires the time of the event");
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

        while (!shouldExit){
            command = in.nextLine();
            executeCommand(command);
        }
    }
}
