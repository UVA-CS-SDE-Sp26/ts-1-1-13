//Ethan Part B
public class FileRecord {

    private final String number;
    private final String filename;

    public FileRecord(String number, String filename){
        this.number = number;
        this.filename = filename;
    }

    public String getNumber(){
        return number;
    }

    public String getFilename(){
        return filename;
    }
}
