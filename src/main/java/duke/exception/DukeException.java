package duke.exception;

/**
 * DukeException represents exceptions that are unique to Duke
 * It stores the error message to be shown to the user
 */
public class DukeException extends Exception {
    private String errorMessage;

    public DukeException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a string that describes the reason for the DukeException being thrown
     *
     * @return the error message that is to be displayed
     */
    public String getErrorMessage(){
        return errorMessage;
    }
}
