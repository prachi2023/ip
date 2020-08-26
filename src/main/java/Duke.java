import java.util.Scanner;

public class Duke {
    public static void printList (Task[] list, int listSize){
        for (int i = 0; i < listSize; i++){
            System.out.print(i+1 + ".");
            list[i].printTaskDetails();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String line;
        Task[] tasks = new Task[10];
        int numOfTasks = 0;
        System.out.println ("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        while (true){
            line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            if (line.equals("list")) {
                System.out.println("This is your list: ");
                printList(tasks, numOfTasks);
                continue;
            }
            if (line.contains("done")){
                int dividerPosition = line.indexOf(" ");
                int taskNumCompleted = Integer.parseInt(line.substring(dividerPosition).trim());

                if (taskNumCompleted > numOfTasks) {
                    System.out.println("This is not a valid task\nPlease enter a valid task number");
                    continue;
                }

                System.out.println("Good Job completing your task! I have marked it as done.");
                tasks[taskNumCompleted - 1].markTaskDone();
                tasks[taskNumCompleted - 1].printTaskDetails();
                continue;
            }
            System.out.println (line);
            tasks[numOfTasks] = new Task(line);
            numOfTasks ++;

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
