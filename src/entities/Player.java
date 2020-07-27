package entities;

import game.Game;

abstract public class Player {
		
	private String name;
	
	public Player( String name ) {
		this.name = name;
	}

	//Pega o nome
	//Retrive the name
	public String getName() {
		return this.name;
	}
	
	//Faz a jogada
	//Do the play
	public abstract void play( Game game  );
	
	
	public static void main( String args[] ) {
		
	}

	//Pega a marca pra preencher o tabuleiro
	//Retrive the mark to fill the table
	abstract public char getMark() ;
	
}
