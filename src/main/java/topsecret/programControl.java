package topsecret;

import java.io.IOException;
import java.util.List;

public class ProgramControl {

    private topsecret.FileHandler fileHandler;

    public ProgramControl(topsecret.FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Main execution logic
     */
    public void execute(String[] args) {

        try {

            // No arguments → list files
            if (args.length == 0) {
                listAvailableFiles();
                return;
            }

            // One argument → display file contents
            String fileNumber = args[0];
            displayFile(fileNumber);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Lists numbered files
     */
    private void listAvailableFiles() throws IOException {

        List<FileRecord> files = fileHandler.listFiles();

        if (files.isEmpty()) {
            System.out.println("No files available.");
            return;
        }

        for (FileRecord file : files) {
            System.out.println(file.getNumber() + " " + file.getFilename());
        }
    }

    /**
     * Displays file contents by number
     */
    private void displayFile(String fileNumber) throws IOException {

        String contents = fileHandler.readFileContents(fileNumber);
        System.out.println(contents);
    }
}

