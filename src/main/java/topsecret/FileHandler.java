//Ethan Part B
package topsecret;

package topsecret;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private final Path dataFolder = Paths.get("data");

    /**
     * Returns a numbered list of text files in the data folder.
     */
    public List<FileRecord> listFiles() throws IOException {

        if (!Files.exists(dataFolder)) {
            throw new IOException("Data folder not found.");
        }

        List<FileRecord> files = new ArrayList<>();

        int counter = 1;

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(dataFolder, "*.txt")) {

            for (Path file : stream) {

                String number = String.format("%02d", counter);
                String filename = file.getFileName().toString();

                files.add(new FileRecord(number, filename));

                counter++;
            }
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
