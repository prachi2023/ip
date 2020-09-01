import java.util.Scanner;

public class Duke {
    public static boolean shouldExit = false;
    public static int numOfTasks = 0;
    public static Task[] tasks = new Task[10];

    // Print the list of tasks entered
    public static void printList (){
        System.out.println("Here is your List of Tasks: ");
        for (int i = 0; i < numOfTasks; i++){
            System.out.println(i+1 + ". " + tasks[i]);
        }
        System.out.println("Now you have " + numOfTasks + " Tasks in the list");
    }

    // Determine the type of task added and add to the existing list of tasks
    public static void addTask (String command){
        int descriptionPosition = command.indexOf(" ");
        if (command.contains("todo")){
            //int descriptionPosition = command.indexOf(" ");
            tasks[numOfTasks] = new ToDo(command.substring(descriptionPosition).trim());
        }
        else if (command.contains ("deadline")){
            int byPosition = command.indexOf("/");
            String description = command.substring(descriptionPosition, byPosition).trim();
            String by = command.substring(byPosition + 3).trim(); // plus 3 to get rid of the /by
            tasks[numOfTasks] = new Deadline(description, by);
        }
        else if (command.contains ("event")) {
           // int descriptionPosition = command.indexOf(" ");
            int atPosition = command.indexOf("/");
            String description = command.substring(descriptionPosition, atPosition).trim();
            String at = command.substring(atPosition + 3).trim(); // plus 3 to get rid of the /by
            tasks[numOfTasks] = new Event(description, at);
        }
        else {
            tasks[numOfTasks] = new Task(command);
        }
        System.out.println("There you go I've added it to the list\n" + tasks[numOfTasks]);
        numOfTasks ++;
    }

    // Determine the command sent and execute accordingly
    public static void executeCommand (String command){
        if (command.equals("bye")){
            shouldExit = true;
            System.out.println("Bye. Hope to see you again soon!");
        }
        else if (command.equals("list")) {
            printList();
        }
        else if (command.contains("done")){
            int dividerPosition = command.indexOf(" ");
            int taskNumCompleted = Integer.parseInt(command.substring(dividerPosition).trim());

            if (taskNumCompleted > numOfTasks) {
                System.out.println("This is not a valid task\nPlease enter a valid task number");
            }

            System.out.println("Good Job completing your task! I have marked it as done.");
            tasks[taskNumCompleted - 1].markTaskDone();
            tasks[taskNumCompleted - 1].printTaskDetails();
        }
        else {
            addTask(command);
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
