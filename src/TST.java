public class TST {
    private Node rootNode;


    // Insert word into TST
    public void insertWordHelper(String word){
        // If the input word is null or empty then stop
        if(word == null || word.isEmpty()){
            return;
        }
        // Call recursive helper starting at the root
        rootNode = insertWordHelper(this.rootNode, word, 0);
    }

    // Recursive helper method that inserts each letter into the TST
    public Node insertWordHelper(Node node, String word, int index){
        char currentC = word.charAt(index);

        // If the node doesn't exist, then create it.
        if(node == null){
            node = new Node();
            node.letter = currentC;
        }

        // Compare current character to node's letter and then decide which direction to go
        if(currentC < node.letter){
            // If the character is smaller, then go left
            node.left = insertWordHelper(node.left,word,index);
        }
        else if(currentC > node.letter){
            // If the character is bigger, then go right
            node.right = insertWordHelper(node.right,word,index);
        }
        else if(index < word.length() - 1){
            // Continue to follow the down the middle if its the right character
            node.middle = insertWordHelper(node.middle, word, index + 1);
        }
        else{
            // Mark the node as a completed word
            node.isWordCompleted = true;
        }
        return node;
    }

    // Look up word in TST
    public boolean lookup(String word){
        // If the word is empty, then return false
        if(word == null || word.isEmpty()){
            return false;
        }
        // Call recursive helper function to find the node
        Node result = findNode(rootNode, word, 0);
        // Return true if the node does exists and is the end of a word
        return result != null && result.isWordCompleted;

    }

    public Node findNode(Node node, String word, int index){
        if(node == null){
            return null;
        }
        char current = word.charAt(index);

        // Compare the nodes and then decide where to go
        if(current < node.letter){
            return findNode(node.left, word, index);
        }
        else if(current > node.letter){
            return findNode(node.right, word, index);
        }
        else if(index < word.length() - 1){
            return findNode(node.middle, word, index + 1);
        }
        else{
            return node;
        }
    }

    public boolean hasPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) return false;
        return findNode(rootNode, prefix, 0) != null;
    }
}
