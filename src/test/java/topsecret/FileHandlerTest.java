// Ethan Part B

package topsecret;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    /**
     * Test that the data folder exists and files are found.
     */
    @Test
    void shouldListFiles() throws IOException {

        FileHandler handler = new FileHandler();

        assertFalse(handler.listFiles().isEmpty(),
                "File list should not be empty.");
    }

    /**
     * Test that files are numbered correctly.
     */
    @Test
    void filesShouldHaveNumbers() throws IOException {

        FileHandler handler = new FileHandler();

        FileRecord first = handler.listFiles().get(0);

        assertNotNull(first.getNumber());
        assertTrue(first.getNumber().length() == 2);
    }

    /**
     * Test reading a real file.
     */
    @Test
    void shouldReadFileContents() throws IOException {

        FileHandler handler = new FileHandler();

        String contents = handler.readFileContents("01");

        assertNotNull(contents);
        assertFalse(contents.isEmpty());
    }

    /**
     * Invalid file number should throw exception.
     */
    @Test
    void shouldThrowOnInvalidFileNumber() throws IOException {

        FileHandler handler = new FileHandler();

        assertThrows(IOException.class, () -> {
            handler.readFileContents("99");
        });
    }

    /**
     * Missing folder should throw exception.
     */
    @Test
    void shouldThrowIfFolderMissing() throws IOException {

        FileHandler handler = new FileHandler("fakeFolder");

        assertThrows(IOException.class, handler::listFiles);
    }
}
