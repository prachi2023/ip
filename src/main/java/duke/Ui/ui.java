package duke.Ui;

public class ui {
    //welcome and exit message
    public void printWelcomeMessage (){
        System.out.println ("Hello! I'm DUKE");
        System.out.println ("What can I do for you?");
    }
    public void printExitMessage (){
        System.out.println ("Bye");
        System.out.println ("See you again!");
    }

    //Error messages
    public void showInvalidTaskNumError (){
        System.out.println("This is not a valid task\nPlease enter a valid task number");
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

    public void showInvalidCommand(){
        System.out.println ("Please enter a valid command");
    }

    public void showNoDescriptionError (){
        System.out.println ("Please enter a description. All Tasks needs a description!");
    }
    public void showNoEventError (){
        System.out.println ("An event requires the time of the event");
    }
    public void showNoDeadlineError (){
        System.out.println ("A Deadline requires the time it is due");
    }

    //Other commands
    public void showEmptyTaskList (){
        System.out.println ("Task List is empty!");
    }

    public void printTask(int index, String task){
        System.out.println (index + ". " + task);
    }

    public void printTaskListInfo (int numTasks){
        System.out.println("You have " + numTasks + " Tasks in the list");
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





}
