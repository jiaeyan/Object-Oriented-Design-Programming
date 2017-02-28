package edu.brandeis.cs12b.pa2;

import edu.brandeis.cs12b.pa2.provided.GameOf15;
import java.util.*;

public class HumanPlayer {

	public static void main(String[] args) throws Exception{
		HumanPlayer hp = new HumanPlayer();
		hp.play(new GameOf15());
	}
	
	/**
	 * Here, you must implement a command-line interface to the 15-puzzle game.
	 * 
	 * You should ask the user for moves and execute them on the provided board
	 * until the puzzle has been solved. The move commands are:
	 * 
	 * u -- move up
	 * d -- move down
	 * l -- move left (that's an L, not an I)
	 * r -- move right
	 * exit -- exit the game
	 * 
	 * Your prompt line should be the following:
	 * "Enter a move: (l)eft, (u)p, (r)ight, (d)own, or (exit): "
	 * 
	 * Invalid input line should be the following:
	 * "Invalid input. Try again."
	 * 
	 * If the move cannot be performed, you should warn:
	 * "*No move was made. Try again."
	 * 
	 * @param args
	 */
	public void play(GameOf15 game) {
		System.out.println(game.toString());
		Scanner console = new Scanner(System.in);
		while (!game.hasWon()){
			System.out.print("Enter a move: (l)eft, (u)p, (r)ight, (d)own, or (exit): ");
			String input =console.next();
			moveProcess(game,input);
		}
		System.out.print("You win!");
		console.close();
		return;
	}
	
	public void moveProcess(GameOf15 game,String input){
		if(input.equals("l")){
			if(!game.moveLeft()){
				System.out.println("No move was made. Try again.");	
			}
			System.out.println(game.toString());
		}else if(input.equals("u")){
			if(!game.moveUp()){
				System.out.println("No move was made. Try again.");				
			}
			System.out.println(game.toString());
		}else if(input.equals("r")){
			if(!game.moveRight()){
				System.out.println("No move was made. Try again.");		
			}
			System.out.println(game.toString());
		}else if(input.equals("d")){
			if(!game.moveDown()){
				System.out.println("No move was made. Try again.");
			}
			System.out.println(game.toString());
		}else if(input.equals("exit")){
			return;
		}else{
			System.out.println("Invalid input. Try again.");
			System.out.println(game.toString());
		}
	}
}
