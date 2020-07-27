package entities;

import game.Game;

public class ComputerPlayerB extends ComputerPlayer{
	
	public ComputerPlayerB(String name) {
		super(name);
	}
	
	@Override
	public void play( Game game ) {
		//Preenche randomicamente a primeira posição vazia  
		//Randomly Fill the first empty position
		int col = (int)(Math.random() * (2 - 0 + 1) + 0);
		int row = (int)(Math.random() * (2 - 0 + 1) + 0);
		
		if( game.canSetPlay(col, row ) ) {
			game.setPlay(col, row, this);
			game.Next();
		}else {
			play( game );
		}
	}
	
}
