package sudoku;

import java.util.ArrayList;
import java.util.Random;
import org.w3c.dom.css.Counter;

/**
 * A class of sudoku puzzle
 * @author Earvin-Carl David
 */
public class Sudoku {
    private int[][] puzzle;

    public Sudoku() {
        puzzle = new int[9][9];
        int counter = 0;
        while (puzzle[0][0] == 0) {
            generatePuzzle();
            counter++;
        }
    }
    
    private void generatePuzzle() {
        ArrayList<Integer> pool;
        
         Random rand = new Random();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                // Step1: initialize the pool
                pool = inirializePool();
                
                // Step2: remove numbers that have been taken in that row
                pool = updatePool(pool, puzzle[i]);
                
                // Step3: remove numbers that have been taken in that col
                pool = updatePool(pool, extractOneColumn(j));
                
                // Step4: remove numbers that have been taken in that square
                pool = updatePool(pool, extractOneSquare(i, j));
                
                // Step5: randomly select a number from the pool
                if (!pool.isEmpty())
                    puzzle[i][j] = pool.get(rand.nextInt(pool.size()));
                else {
                    puzzle = new int[9][9];
                    return;
                }
            }
    }
    
    private ArrayList<Integer> inirializePool() {
        ArrayList<Integer> pool = new ArrayList<>(9);
        
        for (int i = 1; i <= 9; i++)
            pool.add(i);
        
        return pool;
    }
    
    private int [] extractOneColumn(int colIdx) {
        int[] nums = new int[9];
        
        for (int i = 0; i < 9; i++)
            nums[i] = puzzle[i][colIdx];
        
        return nums;
    }
    
    private int[] extractOneSquare(int rowIdx, int colIdx) {
        int[] nums = new int[9];
        
        int rowStartIdx = rowIdx / 3 * 3;
        int colStartIdx = colIdx / 3 * 3;
        
        int counter = 0;
        for (int i = rowStartIdx; i < rowStartIdx + 3; i++)
            for (int j = colStartIdx; j < colStartIdx + 3; j++)
//                nums[counter++] = puzzle[i][j];
                nums[(i - rowStartIdx) * 3 + j - colStartIdx] = puzzle[i][j];
        
        return nums;
    }
    
    private ArrayList<Integer> updatePool(ArrayList<Integer> pool,int[] usedNums) {
        for (int usedNum : usedNums)
            pool.remove((Integer)usedNum);
        
        return pool;
    }

    @Override
    public String toString() {
        String str =  "";
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                str += puzzle[i][j] + " ";
            if (j == 2 || j == 5)
                str += "| ";
            }
            str += "\n";
            if (i == 2 || i == 5)
                str += "------+-------+------\n";
        }
        return str;
    }
    
}
