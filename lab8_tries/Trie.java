package lab8_tries;

public class Trie {
    // Alphabet size (# of symbols) we pick 26 for English alphabet
    static final int ALPHABET_SIZE = 26;


    // class for Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isEndOfWord is true if the node represents end of a word i.e. leaf node
        boolean isEndOfWord;

        TrieNode(){
            isEndOfWord = false;

            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root;
    // If not key present, inserts into trie
    // If the key is prefix of Trie node,
    //  marks leaf node
    static void insert(String key){
        TrieNode currNode = root;

        for(int i = 0; i < key.length(); i++){
            int index = key.charAt(i) - 'a';
            if (currNode.children[index] == null){
                currNode.children[index] = new TrieNode();
            }
            currNode = currNode.children[index];
        }

        currNode.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    static boolean search(String key) {
        TrieNode currNode = root;

        for(int i = 0; i < key.length(); i++){
            int index = key.charAt(i) - 'a';
            if(currNode.children[index] == null){
                return false;
            }
            currNode = currNode.children[index];
        }

        return currNode != null && currNode.isEndOfWord;
    }


    // Driver
    public static void main(String[] args) {

        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
        String[] output = {"Not present in trie", "Present in trie"};

        root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++) {
            insert(keys[i]);
        }

        // Search for different keys
//        System.out.printf("bank: %s\n", search("bank") ? output[1] : output[0]);
//        System.out.printf("banks: %s\n", search("banks") ? output[1] : output[0]);
//        System.out.printf("fill %s\n", search("fill") ? output[1] : output[0]);
//        System.out.printf("filter: %s\n", search("filter") ? output[1] : output[0]);
//        System.out.printf("simple: %s\n", search("simple") ? output[1] : output[0]);

        String key = "bring";

        System.out.printf("%s: %s\n", key, search(key) ? output[1] : output[0]);

    }

    //end of class
}
