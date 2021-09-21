package compression_assignment;

/******************************************************************************
 *  Compilation:  javac Huffman.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  Go to src, then
 *  To compress: java 'path to Huffman.java' compress < 'path to input file' > output file
 *  e.g java compression_assignment\Huffman.java compress < compression_assignment\data\bohemian.txt > bohemianCompressed.txt
 *
 *  To decompress: java 'path to Huffman.java' decompress < 'path to input file' > output file
 *  e.g java compression_assignment\Huffman.java decompress < compression_assignment\data\bohemianCompressed.txt
 *
 ******************************************************************************/


import compression_assignment.helper_code.*;

/**
 *  Add in your information about each method etc. here
 *
 *
 *  @author Your name
 */
public class Huffman {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private Huffman() { }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {
//        Stopwatch timer = new Stopwatch();
        char[] input = BinaryStdIn.readString().toCharArray();  // Reads input from System.in and turns it into char array
        int[] frequency = new int[R];       // R == 256
        String[] codes = new String[R];     // Stores Huffman codes for each char

        for (char c : input) frequency[c]++;    // Counts frequency of each character

        Node root = buildTrie(frequency);       // Builds the trie
        buildCode(codes, root, "");          // Builds the codeword table

        writeTrie(root);                        // Prints trie to be used in decompress()

        BinaryStdOut.write(input.length);       // Prints number of bytes in original uncompressed message, used in decompress()

//        System.out.println("Elapsed Time: " + timer.elapsedTime());

        // Goes through every char in input array
        for (char c : input) {
            String code = codes[c];             // Gets code for current char

            // Goes through every bit in code and writes it to output
            for (int index = 0; index < code.length(); index++) {
                if (code.charAt(index) == '0') BinaryStdOut.write(false);       // '0'
                else if (code.charAt(index) == '1') BinaryStdOut.write(true);   // '1'
                else throw new IllegalStateException("Illegal state");
            }
        }

//        System.out.println("Elapsed Time : " + timer.elapsedTime());

        // close output stream
        BinaryStdOut.close();
    }


    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {
//        Stopwatch timer = new Stopwatch();
        Node root = readTrie();                 // Reads trie that was written by compress()
        int length = BinaryStdIn.readInt();     // Reads number of bytes in original uncompressed message written by compress()

//        System.out.println("Elapsed Time : " + timer.elapsedTime());
        for (int i = 0; i < length; i++) {
            Node x = root;      // Start from root node

            // Traverses the trie until it reaches a leaf node
            while (!x.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();
                x = bit ? x.right : x.left;     // If bit == 1, go right, else go left
            }

            BinaryStdOut.write(x.ch, 8);    // Write char found in leaf node to output
        }

//        System.out.println("Elapsed Time : " + timer.elapsedTime());

        BinaryStdOut.close();
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }


    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }



    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        Stopwatch timer = new Stopwatch();

        if(args[0].equals("compress")){
            compress();
        }else if(args[0].equals("decompress")){
            decompress();
        }else{
            throw new IllegalArgumentException("Illegal Argument");
        }

//        System.out.println(timer.elapsedTime());
    }
}



