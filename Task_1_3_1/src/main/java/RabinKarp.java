import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Rabin–Karp algorithm is a string-searching algorithm that uses hashing
 * to find an exact match of a pattern string in a text.
 * In class:
 * D is a number of accepted symbols,
 * Q is a prime number for mod (%) operation,
 * In algorithm:
 * p - hash of pattern
 * t - hash of text
 */
public class RabinKarp {

    private final InputStream stream;
    private static final int D = 256;
    private static final int Q = 101;

    /**
     * Constructor for my class.
     *
     * @param stream text to find matches
     */
    public RabinKarp(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Rabin–Karp algorithm.
     *
     * @param pattern pattern we have to find in text
     * @return result is a list of indexes in the text where patterns start
     */
    public List<Integer> rabinKarp(String pattern) throws IOException {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            List<Integer> result = new ArrayList<>();

            int patLen = pattern.length();
            int p = 0;
            int t = -1;
            int h = 1;
            int j;
            int i;

            for (i = 0; i < patLen - 1; i++) {
                h = (h * D) % Q;
                p = (D * p + pattern.charAt(i)) % Q;
            }
            p = (D * p + pattern.charAt(i)) % Q;

            int nextChar;
            int start = 0;
            int end = 0;
            int count = 0;
            StringBuilder text = new StringBuilder();

            while (true) {

                // I need one more chance
                if ((nextChar = reader.read()) == -1) {
                    count++;
                    if (count == 2) {
                        break;
                    }
                }

                text.append((char) nextChar);
                end++;

                if (end == patLen + 1) {

                    // Only once
                    if (t == -1) {
                        t = 0;
                        for (i = 0; i < patLen; i++) {
                            t = (D * t + text.charAt(i)) % Q;
                        }
                    }

                    // If pattern hash is equal to text hash
                    if (p == t) {
                        // Check for characters one by one
                        for (j = 0; j < patLen; j++) {
                            if (text.charAt(j) != pattern.charAt(j)) {
                                break;
                            }
                        }
                        if (j == patLen) {
                            result.add(start);
                        }
                    }

                    t = (D * (t - text.charAt(0) * h) + text.charAt(end - 1)) % Q;

                    if (t < 0) {
                        t = (t + Q);
                    }

                    text.deleteCharAt(0);
                    end = patLen;
                    start++;
                }
            }
            return result;
        }
    }
}