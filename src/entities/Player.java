package entities;

import game.Game;

abstract public class Player {
		
	private String name;
	private char mark = ' ';
	
	public Player( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void play( Game game  );
	
	
	public static void main( String args[] ) {
		
	}
	
	public char getMark() {
		return this.mark;
	}
	
}
