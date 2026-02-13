package topsecret;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Cipher {

    private final Map<Character, Character> decodeMap;

    public Cipher(Path keyPath) throws IOException {
        List<String> lines = Files.readAllLines(keyPath);

        if (lines.size() != 2) {
            throw new IllegalArgumentException("Wrong number of lines");
        }

        String real = lines.get(0);
        String cipher = lines.get(1);

        if (real.length() != cipher.length()) {
            throw new IllegalArgumentException("Wrong length");
        }

        decodeMap = new HashMap<>();

        for (int i = 0; i < real.length(); i++) {
            char realChar = real.charAt(i);
            char cipherChar = cipher.charAt(i);

            if (decodeMap.containsKey(cipherChar)) {
                throw new IllegalArgumentException("Duplicate key");
            }

            decodeMap.put(cipherChar, realChar);
        }
    }
    public String decipher(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (decodeMap.containsKey(c)) {
                result.append(decodeMap.get(c));
            }
            else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
