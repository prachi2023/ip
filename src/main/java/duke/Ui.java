package duke;

import java.util.Scanner;

public class Ui {
    //input
    public String readCommand(){
        Scanner in = new Scanner (System.in);
        return in.nextLine();
    }

    //welcome and exit message
    public void printWelcomeMessage (){
        System.out.println ("Hello! I'm DUKE");
        System.out.println ("What can I do for you?");
    }
    public void printExitMessage (){
        System.out.println ("Bye");
        System.out.println ("See you again!");
    }
    public void showLine(){
        System.out.println("----------------------------------------------------------");
    }
    public void showErrorMessage (String errorMessage){
        System.out.println (errorMessage);
    }

    public void showFileLoadingError (){
        System.out.println("File not found");
    }

    public void showFileCreatingError (String fileName){
        System.out.println ("Unable to create a file name "+ fileName);
    }

    public void showFileEditingError (){
        System.out.println ("Unable to edit file");
    }

    //Other commands
    public void showEmptyTaskList (){
        System.out.println ("Task List is empty!");
    }

    public void printTask(int index, String task){
        System.out.println (index + ". " + task);
    }

    public void printTaskListInfo (int numTasks, int numTasksDone){
        System.out.println("You have " + numTasks + " Tasks in the list");
        System.out.println("You have completed " + numTasksDone +" Tasks");
    }

    public void printTaskMarkedDone (String description){
        System.out.println("Good Job completing your task! I have marked it as done.");
        System.out.println(description);
    }
    public void printTaskPreviouslyMarkedDone (String description){
        System.out.println("This task has already been marked as done!");
        System.out.println(description);
    }

    public void printDeleteTaskInfo (String task){
        System.out.println("Alright deleting task: " + task);
        System.out.println("Task has been deleted");
    }

    public void printAddedTask (String task){
        System.out.println("There you go I've added " + task + " to the list");
    }

    public void printNoMatchingTask (){
        System.out.println("There are no tasks that match this keyword");
    }
    public void printMatchingTask(){
        System.out.println("Here are the matching tasks in your list:");
    }

}
