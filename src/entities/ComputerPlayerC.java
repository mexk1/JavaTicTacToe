package entities;

import game.Game;

public class ComputerPlayerC extends Player{
	
	private char mark = 'O';
	private Player[][] plays;
	
	public ComputerPlayerC(String name) {
		super(name);
		this.plays = new Player[3][3];
	}

	@Override
	public void play( Game game ) {
		System.out.println( "Computador jogou" );
		this.plays = game.getPlays();
		checkForWinPlay( game );
		checkIfAdversaryCanWin( game );
		doBetterPlay( game );
		game.Next();
	}
	
	private void doBetterPlay( Game game ) {
		int col = (int)(Math.random() * (2 - 0 + 1) + 0);
		int row = (int)(Math.random() * (2 - 0 + 1) + 0);
		
		if( game.canSetPlay(col, row ) ) {
			game.setPlay(col, row, this);
			game.Next();
		}else {
			doBetterPlay( game );
		}
	}
	
	private void checkForWinPlay( Game game ) {
		if( canWinRows( this, game ) ){
			System.out.println("GANHAR NAS LINHAS");
			this.winOnRows( game );
		}else if( this.canWinCollumns( this, game ) ){
			System.out.println("GANHAR NAS COLUNAS");
			this.winOnCollumns( game );
		}
	}
	
	private void checkIfAdversaryCanWin( Game game ) {
		if( canWinRows( getAdversary(game), game ) ){
			this.blockInRows(getAdversary(game), game );
		}else if( this.canWinCollumns( getAdversary(game), game ) ){
			this.blockInCollumns(getAdversary(game), game );
		}
	}
	
	
	private Player getAdversary( Game game ) {
		for( int i = 0; i < game.getPlayers().length; i++ ) {
			if( !this.equals( game.getPlayers()[i] ) ) {
				return game.getPlayers()[i];
			}
		}
		return game.getPlayers()[1];
	}
	
	
	public void blockInCollumns( Player p, Game g ) {
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[r][c] ) ) inCollum++; 
				
				if( !g.canSetPlay(r, c) ) inCollum--;
				
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
	
	
	public void blockInRows( Player p, Game g ) {
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[r][c] ) ) inCollum++; 
				
				if( !g.canSetPlay(r, c) ) inCollum--;
				
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
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[c][r] ) ) inCollum++; 
				
				if( !g.canSetPlay(c, r) ) inCollum--;
				
			}
			if( inCollum == 2 ) return true;
		}
		return false;
	}
	
	
	
	public boolean canWinRows( Player p, Game g ) {
		for( int r = 0; r < 3; r++ ) {
			int inRow = 0;
			for( int c = 0; c < 3; c++ ) {
				if( p.equals( plays[r][c]  ) ) inRow++; 
				
				if( !g.canSetPlay(r, c) ) inRow--;
			}
			if( inRow == 2 ) return true;
		}
		return false;
	}
	
	public void winOnRows( Game g ) {
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( this.equals( plays[r][c] ) ) inCollum++; 
				//if( !g.canSetPlay(r, c) ) inCollum--;
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
		for( int r = 0; r < 3; r++ ) {
			int inCollum = 0;
			for( int c = 0; c < 3; c++ ) {
				if( this.equals( plays[c][r] ) ) inCollum++; 
				
				//if( !g.canSetPlay(r, c) ) inCollum--;
				
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
	

	public char getMark() {
		return this.mark;
	}
	
}
