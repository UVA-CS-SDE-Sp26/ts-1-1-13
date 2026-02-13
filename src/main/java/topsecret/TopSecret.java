package topsecret;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command Line Utility (CLI)
 *
 * Usage:
 *   java  topsecret.TopSecret
 *   java topsecret.TopSecret <fileNumber>
 *   java topsecret.TopSecret <fileNumber> <keyFile>
 *
 * Notes:
 * - <fileNumber> can be "1" or "01" (we normalize to two digits)
 * - Default key is searched as "key.txt" first, then "ciphers/key.txt"
 * - Files are expected to live in the "data/" folder (per FileHandler)
 */
public class TopSecret {

    public static void main(String[] args) {
        System.out.println("WD = " + Paths.get("").toAbsolutePath());
        FileHandler fh = new FileHandler();

        try {
            // 0 args: list files + show usage
            if (args.length == 0) {
                listAvailableFiles(fh);
                printUsage();
                return;
            }

            // 1 or 2 args: decipher file with default or provided key
            if (args.length == 1 || args.length == 2) {
                String normalizedFileNumber = normalizeFileNumber(args[0]);

                // read the encrypted contents via teammate FileHandler
                String encrypted = fh.readFileContents(normalizedFileNumber);

                Path keyPath;
                if (args.length == 2) {
                    keyPath = Paths.get(args[1]);
                    if (!Files.exists(keyPath)) {
                        System.out.println("Error: key file not found: " + keyPath);
                        return;
                    }
                } else {
                    keyPath = getDefaultKeyPath();
                    if (keyPath == null) {
                        System.out.println("Error: default key file not found. Expected key.txt or ciphers/key.txt");
                        return;
                    }
                }

                Cipher cipher = new Cipher(keyPath);
                String decrypted = cipher.decipher(encrypted);

                System.out.println(decrypted);
                return;
            }

            // >2 args: usage
            System.out.println("Error: too many arguments.");
            printUsage();

        } catch (NumberFormatException e) {
            System.out.println("Error: fileNumber must be an integer (example: 1 or 01).");
        } catch (IOException e) {
            // FileHandler throws IOException for invalid file number, missing folder, etc.
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Cipher throws IllegalArgumentException for bad key formats (duplicate key, wrong lines, etc.)
            System.out.println("Error: invalid key file: " + e.getMessage());
        }
    }

    private static void listAvailableFiles(FileHandler fh) throws IOException {
        List<FileRecord> files = fh.listFiles();
        System.out.println("Available files:");
        for (FileRecord r : files) {
            // assuming teammate FileRecord has these getters (your screenshot shows getNumber/getFilename)
            System.out.println(r.getNumber() + " " + r.getFilename());
        }
        System.out.println();
    }

    /**
     * Accepts "1" or "01" and returns "01".
     * Throws NumberFormatException if not numeric.
     */
    private static String normalizeFileNumber(String input) {
        String trimmed = input.trim();

        // If it's already 2 digits like "01", parse to int then reformat
        int n = Integer.parseInt(trimmed);
        if (n < 0) throw new NumberFormatException("negative");
        return String.format("%02d", n);
    }

    /**
     * Default key: prefer ./key.txt, fallback to ./ciphers/key.txt
     * Returns null if neither exists.
     */
    private static Path getDefaultKeyPath() {
        Path p1 = Paths.get("key.txt");
        if (Files.exists(p1)) return p1;

        Path p2 = Paths.get("ciphers", "key.txt");
        if (Files.exists(p2)) return p2;

        return null;
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java topsecret.TopSecret");
        System.out.println("      Lists available files and shows how to run the program.");
        System.out.println();
        System.out.println("  java topsecret.TopSecret <fileNumber>");
        System.out.println("      Deciphers the selected file using the default key.");
        System.out.println();
        System.out.println("  java topsecret.TopSecret <fileNumber> <keyFile>");
        System.out.println("      Deciphers the selected file using the provided key file.");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java topsecret.TopSecret 1");
        System.out.println("  java topsecret.TopSecret 01");
        System.out.println("  java topsecret.TopSecret 2 myKey.txt");
    }
}