package entities;

import game.Game;

public class ComputerPlayerA extends ComputerPlayer {
	
	public ComputerPlayerA(String name) {
		super(name);
	}

	@Override
	public void play( Game game ) {
		//Preenche a primeira posição vazia
		//Fill the first empty position
		System.out.println( "Vez do computador" );
		for( int i = 0; i < game.getTable().getRows(); i++ ) {
			for( int j = 0; j < game.getTable().getCols(); j++ ) {
				if( game.canSetPlay(i, j ) ) {
					game.setPlay(i, j, this);
					game.Next();
				}
			}
		}
	}
	
}
