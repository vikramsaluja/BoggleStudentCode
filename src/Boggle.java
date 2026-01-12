import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // Build the TST from the dictionary
        TST tst = new TST();
        for(String word : dictionary){
            tst.insertWordHelper(word);
        }

        // Set variables for the size of board (rows and cols)
        int rows = board.length;
        int cols = board[0].length;

        // Variable to keep track if a certain cell has been visited or not
        boolean[][] visited = new boolean[rows][cols];

        // Start DFS from every cells
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                DFS(board, i, j, tst, "", goodWords, visited);
            }
        }
        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public static void DFS(char[][] grid, int i, int j, TST tst, String current, ArrayList<String> words, boolean[][] isVisited){


        // First base case to check for anything that might be out of bounds
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        // Second based case to check if it is already used in this path
        if (isVisited[i][j]) {
            return;
        }

        // Build new word by adding current cell
        String newWord = current + grid[i][j];

        // Prefix pruning: stop if no dictionary word starts with this prefix
        if (!tst.hasPrefix(newWord)) {
            return;
        }

        // Mark visited
        isVisited[i][j] = true;

        // If it's a full dictionary word, add it (avoid duplicates)
        if (tst.lookup(newWord)) {
            if (!words.contains(newWord)) {
                words.add(newWord);
            }
        }

        // Make sure to explore all four different directions with no diagonals
        DFS(grid, i - 1, j, tst, newWord, words, isVisited);
        DFS(grid, i + 1, j, tst,newWord, words,isVisited);
        DFS(grid, i, j - 1, tst, newWord, words,isVisited);
        DFS(grid, i, j + 1, tst, newWord, words, isVisited);

        // Unmark so other paths can use it
        isVisited[i][j] = false;
    }
}
