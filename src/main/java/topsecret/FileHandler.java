//Ethan Part B
package topsecret;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private final Path dataFolder = Paths.get("data");

    /**
     * Returns a numbered list of text files in the data folder.
     * Option A: Sort filenames alphabetically BEFORE numbering to keep ordering consistent.
     */
    public List<FileRecord> listFiles() throws IOException {

        if (!Files.exists(dataFolder)) {
            throw new IOException("Data folder not found.");
        }

        List<Path> paths = new ArrayList<>();

        // Collect all .txt files first
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dataFolder, "*.txt")) {
            for (Path file : stream) {
                paths.add(file);
            }
        }

        // Sort alphabetically by filename so numbering is stable
        paths.sort((p1, p2) ->
                p1.getFileName().toString().compareToIgnoreCase(p2.getFileName().toString())
        );

        List<FileRecord> files = new ArrayList<>();
        int counter = 1;

        // Number AFTER sorting
        for (Path file : paths) {
            String number = String.format("%02d", counter);
            String filename = file.getFileName().toString();
            files.add(new FileRecord(number, filename));
            counter++;
        }

        return files;
    }

    /**
     * Reads the contents of a file based on its number.
     */
    public String readFileContents(String fileNumber) throws IOException {

        for (FileRecord file : listFiles()) {
            if (file.getNumber().equals(fileNumber)) {
                Path filePath = dataFolder.resolve(file.getFilename());
                return Files.readString(filePath);
            }
        }

        throw new IOException("Invalid file number.");
    }
}