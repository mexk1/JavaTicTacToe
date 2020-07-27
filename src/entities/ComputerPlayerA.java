package entities;

import game.Game;

public class ComputerPlayerA extends Player {

	private char mark = 'O';
	
	public ComputerPlayerA(String name) {
		super(name);
	}

	@Override
	public void play( Game game ) {
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
	

	public char getMark() {
		return this.mark;
	}

}
