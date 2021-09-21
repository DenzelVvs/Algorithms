package lab7_substring_search;

public class BruteForceSearch {
    public static void search(String txt, String pat)
    {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++)
        {
            int j;
            for (j = 0; j < M; j++)
            {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == M)
            {
                System.out.println(i);
                System.out.println(txt.substring(i,i + M));
            }
        }
    }

    public static void main(String[] args)
    {
        //alter to take text file in..
        String txt = "ABABDABACDABABCABABBDSABABCABAB";
        String pat = "ABABCABAB";
        search(txt, pat);
    }
}
