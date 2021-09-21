package lab7_substring_search;

import java.util.Arrays;

public class KMPSearch {
    static void search(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int[] lps = new int[M];
        int patIndex = 0; // index for pat[]
        int txtIndex = 0; // index for txt[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);
//        System.out.println(Arrays.toString(lps));

        while (txtIndex < N) {
            if (pat.charAt(patIndex) == txt.charAt(txtIndex)) {
                patIndex++;
                txtIndex++;
            }

            // Pattern has been found in txt
            if (patIndex == M) {
                System.out.println(txt.substring(txtIndex - patIndex, txtIndex - patIndex + M));
                patIndex = lps[patIndex - 1];
            }

            // mismatch after patIndex matches
            else if (txtIndex < N && pat.charAt(patIndex) != txt.charAt(txtIndex)) {
                if (patIndex != 0)
                    patIndex = lps[patIndex - 1];
                else
                    txtIndex++;
            }
        }

    }

    static void computeLPSArray(String pat, int M, int[] lps)
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        search(pat, txt);
    }
}
