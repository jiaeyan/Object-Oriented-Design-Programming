package edu.brandeis.cs12b.pa2;

import edu.brandeis.cs12b.pa2.provided.GameOf15;
import edu.brandeis.cs12b.pa2.provided.Point;

public class ComputerPlayer {

	public static void main(String[] args) {
		ComputerPlayer comp = new ComputerPlayer();
		System.out.println(comp.solveRelaxed(new GameOf15()));
	}

	
	/**
	 * Here, you should write a method that will print a sequence of moves to solve the GameOf15. Finally,
	 * return the solved GameOf15.
	 * 
	 * However, you don't have to solve the entire game, you merely have to get the blank space
	 * into the right place.
	 * 
	 * In other words, if your board looks like this:
	 * 
	 * 1	13	6	2	
     * 5	8	10	4	
     * 9	7	3	11	
     * 14	15		12 
	 * 
	 * then you need to make only one move (left!!!), in order to get this board:
	 * 
	 * 1	13	6	2	
     * 5	8	10	4	
     * 9	7	3	11	
     * 14	15	12	
     * 
	 * You **must** not modify GameOf15.java for this part.
	 * 
	 * Print out each move on its own line using the same letters as before.
	 * u -- move up
	 * d -- move down
	 * l -- move left (that's an L, not an I)
	 * r -- move right
	 * 
	 * @return the solved game of 15 board
	 */
	public GameOf15 solveRelaxed(GameOf15 game) {
		Point blankPoint= game.findBlank();
		int colNum=blankPoint.col;
		int rowNum=blankPoint.row;
		for(int i=0;i<3-colNum;i++){
			if(game.moveLeft()){
				blankPoint.col+=1;
				System.out.println("l");	
			}
		}
		for(int j=0;j<3-rowNum;j++){
			if(game.moveUp()){
				blankPoint.row+=1;
				System.out.println("u");	
			}
		}
		return game;
	}
	
	/**
	 * BONUS QUESTION
	 * 
     * Solving the entire board can be done for an extra credit! 
     * We suggest you to try and solve the entire board with some help from Google, as some of
     * the algorithms can be pretty complex. One option (not the easiest) 
     * is to use the A* search algorithm.
     * 
     * This might help you get started: http://stackoverflow.com/questions/94975/how-do-you-solve-the-15-puzzle-with-a-star-or-dijkstras-algorithm
	 * 
	 * You **must** not modify GameOf15.java for this part.
	 * 
	 * Print out each move on its own line using the same letters as before.
	 * u -- move up
	 * d -- move down
	 * l -- move left (that's an L, not an I)
	 * r -- move right
	 * 
	 * @return the solved game of 15 board
	 */
	public GameOf15 solveReal(GameOf15 game) {
		//implement me for an extra credit!!!
		//if you decided not to implement this part, don't touch the line below.
		//if you decided to implement this part, remove the next line and implement your solution.
		throw new UnsupportedOperationException("Bonus question not implemented");
	}
}
