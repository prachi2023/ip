public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String toString (){
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, by);
    }
}
