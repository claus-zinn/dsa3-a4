package de.unituebingen.sfs.npuzzle;

// some Java libraries to consider
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Iterator;
import java.util.Comparator;

/**
 * Hello NN Puzzle world!
 *
 */
public class NNPuzzle 
{
    private int[] tiles;
    
    // constructor taking N
    public NNPuzzle( int N ) throws Exception{
    }

    // constructor, using array of ints
    public NNPuzzle( int[] tiles ) {
    }

    @Override
    public boolean equals(Object obj) {

	// given
        if (this == obj) { return true;  }
	if (obj == null) { return false; }
	
        if (getClass() != obj.getClass()) {
            return false;
        }

        NNPuzzle pobj = (NNPuzzle) obj;
			
        // two puzzles are equal when they have their tiles positioned equally.
	// YOUR CODE COMES HERE

	return true;
    }

    // given, do not change or delete.
    @Override
    public int hashCode() {
	return Arrays.hashCode(tiles);
    }
    
    public List<NNPuzzle> successors() {

	// The following is not binding (but the code compiles ;-) 
	List<NNPuzzle> successorList = new ArrayList<NNPuzzle>();
	// YOUR CODE HERE
	return successorList;
    }

    public void easyShuffle( int numberOfMoves ) {
    }

    public void knuthShuffle() {
    }

    public boolean isSolvable() {
	return false;
    }

    public boolean isSolved() {
	return false;
    }

    public void createStartState() {
    }

    public int hamming() {
	return 0;
    }

    public int manhattan() {
	return 0;
    }

    public static void blindSearch( NNPuzzle startState){
    }
    
    public static void heuristicSearch( NNPuzzle startState ){
    }
    
    private void printState(){
    }
    
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello N*N-Puzzle World!" );

	try {

	} catch (Exception e) {
            System.out.printf("Exception!");
        }
    }
}
