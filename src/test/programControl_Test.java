public class programControl_Test {

    /*
     * Test 1: Correct file name
     * Expectation:
     * File contents are read
     * Readable text is returned / printed
     */
    public static void testCorrectFileName() {
        System.out.println("TEST 1: Correct file name");

        String[] args = {"testfile.txt"};
        programControl.file(args);

        System.out.println("Expected: Contents of testfile.txt printed\n");
    }

    /*
     * Test 2: Incorrect file name
     * Expectation:
     * - No contents returned
     * - Error message displayed
     */
    public static void testIncorrectFileName() {
        System.out.println("TEST 2: Incorrect file name");

        String[] args = {"doesNotExist.txt"};
        Program.main(args);

        System.out.println("Expected: File not found error\n");
    }

    /*
     * Test 3: No command-line arguments
     * Expectation:
     * directory content is printed and file is numbered
     */
    public static void testNoArguments() {
        System.out.println("TEST 3: No command-line arguments");

        String[] args = {};
        Program.main(args);

        System.out.println("Expected: Numbered list of available files\n");
    }

    /*
     * Test 4: Empty file
     * Expectation:
     * Program does not crash No content or file is empty message shown
     */
    public static void testEmptyFile() {
        System.out.println("TEST 4: Empty file");

        String[] args = {"empty.txt"};
        Program.main(args);

        System.out.println("Expected: No output or empty file message\n");
    }

    /*
     * Test 5: Command line input handling
     * Expectation:
     * Input string is passed correctly to file handler
     */
    public static void testCommandLineHandling() {
        System.out.println("TEST 5: Command line handling");

        String[] args = {"example.txt"};
        Program.main(args);

        System.out.println("Expected: example.txt is read correctly\n");
    }

    /*
     * Runs all tests
     */
    public static void main(String[] args) {
        testCorrectFileName();
        testIncorrectFileName();
        testNoArguments();
        testEmptyFile();
        testCommandLineHandling();

        System.out.println("All tests executed.");
    }



}
