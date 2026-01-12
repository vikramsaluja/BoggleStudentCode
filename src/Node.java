public class Node {
    // Character stored at the node
    char letter;
    // Check if the node is the end of a word
    boolean isWordCompleted;
    // Goes toward nodes with smaller letters
    Node left;
    // Goes to the next letter in the current word
    Node middle;
    // Goes to node with larger letters
    Node right;
}
