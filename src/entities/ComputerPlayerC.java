package entities;

import game.Game;

public class ComputerPlayerC extends ComputerPlayer{
	
	private Player[][] plays;
	
	public ComputerPlayerC(String name) {
		super(name);
		this.plays = new Player[3][3];
	}

	@Override
	public void play( Game game ) {
		this.plays = game.getPlays();
		
		checkForWinPlay( game );
		
		checkIfAdversaryCanWin( game );
		
		doRandomPlay( game );
		
		System.out.println( "Computador jogou" );
		
		game.Next();
	}
	
	private void doRandomPlay( Game game ) {
		//Faz uma jogada aleatória
		//Do a random play
		int col = (int)(Math.random() * (2 - 0 + 1) + 0);
		int row = (int)(Math.random() * (2 - 0 + 1) + 0);
		
		if( game.canSetPlay(col, row ) ) {
			game.setPlay(col, row, this);
			game.Next();
		}else {
			doRandomPlay( game );
		}
	}
	
	private void checkForWinPlay( Game game ) {
		//Verifica se há possibilidade de ganhar em linhas ou colunas, se sim, faz as jogada.
		//Check if can win on rows or columns, if yes, do the play
		if( canWinRows( this, game ) ){
			this.winOnRows( game );
		}else if( this.canWinCollumns( this, game ) ){
			this.winOnCollumns( game );
		}
	}
	
	private void checkIfAdversaryCanWin( Game game ) {
		//Verifica se há possibilidade do adversario ganhar em linhas ou colunas, se sim, faz as jogada.
		//Check if the adversary can win on rows or columns, if yes, do the play
		if( canWinRows( getAdversary(game), game ) ){
			this.blockInRows(getAdversary(game), game );
		}else if( this.canWinCollumns( getAdversary(game), game ) ){
			this.blockInCollumns(getAdversary(game), game );
		}
	}
	
	
	private Player getAdversary( Game game ) {
		//Pega o jogador adversário
		//Get the adversary player
		for( int i = 0; i < game.getPlayers().length; i++ ) {
			if( !this.equals( game.getPlayers()[i] ) ) {
				return game.getPlayers()[i];
			}
		}
		return game.getPlayers()[1];
	}
	
	
	public void blockInCollumns( Player p, Game g ) {
		//Bloquiea a jogada do adversário nas colunas
		//Block the adversary play in columns
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[c][r] ) ) inCollum++; 				
			}
			if( inCollum == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(j, r) ) {
						g.setPlay(j, r, this );
						g.Next();
					}
				}
			}
		}
	}
	
	
	public void blockInRows( Player p, Game g ) {
		//Bloquiea a jogada do adversário nas linhas
		//Block the adversary play in rows
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[r][c] ) ) inCollum++; 
			}
			if( inCollum == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(r, j) ) {
						g.setPlay(r, j, this );
						g.Next();
					}
				}
			}
		}
	}
	
	
	public boolean canWinCollumns( Player p, Game g ) {

		//Verifica se um jogador pode ganhar nas colunas
		//Check if a player can win on columns
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[c][r] ) ) inCollum++; 				
			}

			if( inCollum == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(j, r) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	public boolean canWinRows( Player p, Game g ) {

		//Verifica se um jogador pode ganhar nas linhas
		//Check if a player can win on rows
		for( int r = 0; r < 3; r++ ) {
			int inRow = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[r][c]  ) ) inRow++; 
			}
			if( inRow == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(r, j) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void winOnRows( Game g ) {
		//Faz a jogada vencedora em linhas
		//Do the win play on rows
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( this.equals( plays[r][c] ) ) inCollum++; 
			}
			if( inCollum == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(r, j) ) {
						g.setPlay(r, j, this );
						g.Next();
					}
				}
			}
		}
	}
	
	public void winOnCollumns( Game g ) {
		//Faz a jogada vencedora em colunas
		//Do the win play on columns
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( this.equals( plays[c][r] ) ) inCollum++; 				
			}
			if( inCollum == 2 ) {
				for( int j = 0; j < 3; j++ ) {
					if( g.canSetPlay(j, r) ) {
						g.setPlay(j, r, this );
						g.Next();
					}
				}
			}
		}
	}
	
}
