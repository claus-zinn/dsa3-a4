# Assignment 4 (*graded*):

*This is a graded assignment. The assignment is due on Tuesday November 20, 2018 at 11pm local time. Later github commits will
 be disgarded.* Please respect the class' [policy](https://dsacl3-2018.github.io/policy.html). Happy coding!


![npuzzle](https://github.com/claus-zinn/dsa3-a4/blob/master/145px-15-puzzle_magical.svg.png "15-puzzle")

## The assignment

The N puzzle is a game invented and popularized by Noyes Palmer Chapman in the 1870s. It is played
on an N-by-N grid with $N^2 - 1$ tiles labeled 1 through N-1 and a blank square. Your goal is to
rearrange the tiles so that they are in order. You are permitted to slide one of the available
tiles horizontally or vertically (but not diagonally) into the blank square. In this assignment,
you will write a program that solves the puzzle automatically. Your program will be capable of
solving the puzzle for N=3, but also for N=4, N=5, and N=6. For this, you will need to implement
two search strategies: blind search and informed search.

In the github repository, you'll find the puzzle skeleton code to get started. For the sake of
simplicity, all the methods will be implemented in the NNPuzzle class.

## 4.1 State representation

## 4.1a

The N puzzle is represented as an array of integers. The 15-puzzle shown above, for
instance, can be represented as an array of integers [15,2,1,12,8,5,6,11,4,9,10,7,3,14,13,0] with
'0' denoting the blank. Write a constructor method

~~~{.java}
public NNPuzzle( int N ) throws Exception
~~~

that

- checks whether the board size is at least 3 (yielding a 3x3 board) and at most 6 (yielding a 6x6
  board), otherwise throws an exception;

- allocates an integer array of the correct size, fills the array with the "sorted" puzzle, and
  assigns the array to an instance variable, say tiles.

## 4.1b
Overwrite the *equals* method that tests whether a given NNPuzzle object is equal to another NNPuzzle instance.
For this, fill in the missing pieces in the skeleton.

## 4.2 Successor function

For a given NNPuzzle, write a method that returns all *direct* successor states.

~~~{.java}
public List<NNPuzzle> successors() 
~~~

Hint 1: it is easier to move the blank around rather than the
tiles. In the example puzzle shown above, you can move the blank up (exchange position with 7) and
left (exchange position with 13). These are the two direct successor states.

Hint 2: start with locating the blank tile. Once you have its position, check whether it can be moved
left, right, up, and down. For left and right movement, consider the remainder operator *%*. For up, check
whether your blank is not in the top row; for down, check whether your blank is not in the bottom
row. Your solution should work for all allowed board sizes.

Hint 3: consider the clone() method to make a copy of a given board. Also, consider implementing a
second constructor that gets as argument an array of integers.

## 4.3a Generate Random State (First Approach)

Given a *solved* start state returned by the constructor built in 4.1a (all tiles are in the correct order),
write a method that shuffles the board.  In the first approach, you are going to use the successor
function from 4.2. Write a method that makes a given number of random moves. For this, it selects a
random successor state from all possible (direct) successor states of a given state, makes this
move, and repeats this action numberOfMoves times.

~~~{.java}
public void easyShuffle( int numberOfMoves )
~~~

## 4.3b Generate Random State (Second Approach) 

Write a method
~~~{.java}
public void knuthShuffle()
~~~

that uses the Knuth shuffle introduced in class. Beware, please leave the blank in its *home*
position. That is, it should stay at the end of the tiles array.


## 4.3c

Unfortunately, not all random states generated by the Knuth shuffle are solvable. In fact, an N-Puzzle
is only solvable iff it has an even number of "inversions". Here, whenever a tile is preceded by a tile
with a higher value it counts as an inversion. With the blank space in the home position, the number
of inversions must be even for the puzzle to be solvable.

Write a method isSolvable that counts the number of inversions on a given board. It returns true, whenever
the number of inversions on the given board is even.

~~~{.java}
public boolean isSolvable()
~~~

## 4.3d
Now, write a method to initialize the problem state with a solvable Knuth randomization:

~~~{.java}
public void createStartState() 
~~~

Your method should call the Knuth shuffle until a randomized board is found solvable.

## 4.3e
Finally, write a method that checks whether a given puzzle is in a solved state.

~~~{.java}
public boolean isSolved() 
~~~


Recap: We have a representation of the N*N board, we can produce solvable random states, and we can
generate successor states for each given state. In the following, we will produce code that can
solve the puzzle automatically. For this, we start with blind search.


## 4.4 Blind search.

For the following, we will use two auxiliary data structures: an *open list* and a *closed list*.
The *open list* will hold all NNPuzzle states that we need to look at to check whether it is a
solved state or not. The *closed list* is used to prevent cycles. Whenever a state has been
unsuccessfully examined for the *goal state* property (isSolved returned false), we add the state
to the closed list. A successor state for a given state will be added to the open list only when it
has not been seen before (that is, is hasn't been added to the closed list in the past, and it's
not already in the open list).

Write a method

~~~{.java}
public static void blindSearch( NNPuzzle startState )
~~~

that solves the N puzzle for a given N. The method works as follows, assuming that the
OPEN list is initiated with the start state that resulted from a good shuffle:

1. Take out the first element from the OPEN list.

2. If the state is the goal state, terminate.

3. Otherwise, add the state to the closed list of seen states. Then call *successors* to get the
successor states for the examined state. For each state, if a successor state is not in the closed
list (and not in the OPEN list already), add the state to the OPEN list.

4. Continue with 1.

Hint: Use stacks for maintaining the open list. This will implement *depth-first search*.


## 4.5 Use of Heuristic Information

Blind search finds its limits with increasing puzzle size N. For this, we will implement informed
search and some helper routines that judge the "quality" of a given state, that is, its estimated
distance to the goal state.

## 4.5a Number of misplaced tiles

Write the method
~~~{.java}
public int hamming() 
~~~

that counts the number of misplaced tiles.

E.g., the hamming distance for the given board above is 13. Only the blank, 2, and 14 are in their home position.

## 4.5b Manhattan distance

Write the method
~~~{.java}
public int manhattan() 
~~~

that counts for each tile its distance from its home location.

E.g., tile 15 is 5 moves away from its home location (one possible sequence of moves is right,
right, down, down, down).


## 4.6 Heuristic Search

For informed search, you will now use a Priority Queue for maintaining the *open list*. For this,
states must now be associated with their goal distance (either hamming or manhattan). The state
with the minimal distance should be at the top of the Priority Queue. This implements *best-first search*.

## 4.6a
Introduce an instance variable *goalDistance* to hold the value of *manhattan()* or *hamming()*

## 4.6b  Use a *PriorityQueue* to maintain the open list.

For this, overwrite its *compare* function that compares two given puzzles *p1* and *p2*.

## 4.6c Write the method
~~~{.java}
public static void heuristicSearch( NNPuzzle startState )
~~~

that implements heuristic search. It is similar to blind search, but now you are using a priority
queue rather than a simple list. Also, when you add a state to the priority queue, compute its goal
distance value first.

## 4.7 Analysis

Analyse the computational complexity of your blind and heuristic search for N=3, N=4, N=5, and N=6.
Write some driver code that calls blind search and informed search for the same shuffled board. To
support your analysis:

- use StopWatch to measure the time needed to solve a given puzzle
- extend your search methods by counting the number of states examined.

Fill out the table and submit it as text file to the
repository:

| N   | time (blind) | time (informed/hamming) | time (informed/manhattan) | states examined (informed/hamming) | states examined (informed/manhattan) 
| --- | ------------ | ----------------------- | ------------------------- | ---------------------------------- | ---------------------------------- |
| 3   |        .     | .                       |              .            |  .                                 |   .                                |
| 4   |        .     | .                       |              .            |  .                                 |   .                                |
| 5   |        .     | .                       |              .            |  .                                 |   .                                |
| 6   |        .     | .                       |              .            |  .                                 |   .                                |

Which heuristic function is better? Is manhattan producing solutions with shorter move sequences than hamming, see bonus round!

## 4.8 Bonus

- Extend your code to mainain a *solution trace*. The trace shows all the *moves* to transform the
  initial state into the goal state.

- Once a solution is found, define a method *printState* to print all intermediate states between initial state and goal state.

- Use *a-star* rather than best-first search. For this, the cost incurred for generating a state
  needs to be taken into account as well. E.g., a initial state has cost 0, its direct successor has
  cost 1, etc.


## Hints:

- introduce a new instance variable incurredCost, which is incremented in successors()
- introduce a new instance variable parent, which points to the predecessor state of a given state.


Again, happy coding!




