package com.krasnyanskii;

public class SubString {
    /**
     * d is the number of characters in the input alphabet
     */
    public final static int d = 256;

    /**
     *
     * @param pat pattern
     * @param txt text
     * @param q A prime number
     */
    static void search(String pat, String txt, int q)
    {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;

        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        for (i = 0; i <= N - M; i++) {

            if (p == t) {
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }

            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;

                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args)
    {
        String txt = "Testing Rabin-Karp algorithm";
        String pat = "algorithm";
        int q = 101; // A prime number
        search(pat, txt, q);
    }
}