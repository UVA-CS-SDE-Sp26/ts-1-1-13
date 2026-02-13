//Ethan Part B
package topsecret;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private final String filesFolder = "data" + FileSystems.getDefault().getSeparator();

    public List<FileRecord> listFiles() throws IOException {

        Path folderPath = Paths.get(filesFolder);

        if (!Files.exists(folderPath)) {
            throw new IOException("Data folder not found.");
        }

        List<FileRecord> records = new ArrayList<>();

        int counter = 1;

        DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath, "*.txt");

        for (Path file : stream) {

            String number = String.format("%02d", counter);
            String filename = file.getFileName().toString();

            records.add(new FileRecord(number, filename));

            counter++;
        }

        return records;
    }


    public String readFileContents(String fileNumber) throws IOException {

        List<FileRecord> files = listFiles();

        for (FileRecord record : files) {

            if (record.getNumber().equals(fileNumber)) {

                Path targetPath = Paths.get(filesFolder + record.getFilename());

                if (!Files.exists(targetPath)) {
                    throw new IOException("File not found.");
                }

                StringBuilder content = new StringBuilder();

                try (BufferedReader reader = Files.newBufferedReader(targetPath)) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }

                return content.toString();
            }
        }

        throw new IOException("Invalid file number.");
    }
}
