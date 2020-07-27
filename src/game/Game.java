package game;


import java.util.Scanner;

import entities.ComputerPlayerA;
import entities.ComputerPlayerB;
import entities.ComputerPlayerC;
import entities.HumanPlayer;
import entities.Player;

public class Game {
	
	private Table table;
	private Player[] players;
	private Scanner scanner;
	private Player currentPlayer;
	private Player[][] plays;
	private boolean[][] filled;
	
	public Game(){
		scanner = new Scanner( System.in );
		this.preGame();
		this.generateTable();
		this.run();
	}
		
	
	public static void main( String[] args ) {
		new Game();
	}	

	private void run() {
		this.plays = new Player[table.getRows()][table.getRows()];
		this.filled = new boolean[table.getRows()][table.getRows()];
		
		for( int i = 0; i < table.getRows(); i++ ) {
			for( int j = 0; j < table.getCols(); j++ ) {
				filled[i][j] = false;
			}
		}
		
		
		this.table.render( this );
		int x = (int) Math.round( Math.random() );
		currentPlayer = players[x];
		currentPlayer.play( this );
	}
	
	
	
	private void preGame() {
		System.out.println( "Digite seu nome" );
		String name = scanner.nextLine();
		this.players = new Player[2];
		this.players[0] = new HumanPlayer( name );
		this.chooseDifficulty();
		System.out.println( "Voce é o X" );
	}
	
	
	
	private void chooseDifficulty() {

		System.out.println( "Escolha a dificuldade: A, B, ou C." );
		String difficulty = scanner.next();

		switch( difficulty ) {
		
			case "A": 
				System.out.println( "Dificuldade: A." );
				this.players[1] = new ComputerPlayerA( "Computador" );
				break;
			case "B": 
				System.out.println( "Dificuldade: B" );
				this.players[1] = new ComputerPlayerB( "Computador" );
				break;
			case "C": 
				System.out.println( "Dificuldade: C" );
				this.players[1] = new ComputerPlayerC( "Computador" );				
				break;
			default:
				this.chooseDifficulty();
				break;
		}
	}
	
	
	
	private void generateTable() {
		table = new Table();
	}
	
	
	
	public void Next(){
		this.table.render( this );
		if( this.checkWinner() ) {
			this.winner( currentPlayer );
		}else if( isADraw() ){
			System.out.println("Empatou");
		}else {
			if( currentPlayer.equals( players[0] ) ) {
				currentPlayer = players[1];
			}else {
				currentPlayer = players[0];
			}
			currentPlayer.play( this );
		}
	}
	
	
	public boolean checkWinner() {
		
		for( int i = 0; i < players.length; i++ ) {
			boolean isWinner = winnerCollumns( players[i] );
			if( isWinner ) return isWinner;
			
			isWinner = winnerRows( players[i] );
			if( isWinner ) return isWinner;
			
			isWinner = winnerDiagonals( players[i] );
			if( isWinner ) return isWinner;
		}
		
		return false;
	}
	
	
	public boolean winnerDiagonals( Player p ) {
		if(
			p.equals( plays[0][0] ) &&
			p.equals( plays[1][1] ) &&
			p.equals( plays[2][2] ) 
		) return true;
		
		if(
			p.equals( plays[0][2] ) &&
			p.equals( plays[1][1] ) &&
			p.equals( plays[2][0] ) 
		) return true;
		
		return false;
	}
	
	public boolean winnerCollumns( Player p ) {
		for( int r = 0; r < table.getRows(); r++ ) {
			boolean hasDiferent = false;
			for( int c = 0; c < table.getCols(); c++ ) {
				if( !p.equals( plays[c][r]  ) ) hasDiferent = true; 
			}
			if( !hasDiferent ) return true;
		}
		return false;
	}
	
	public boolean winnerRows( Player p ) {
		for( int r = 0; r < table.getRows(); r++ ) {
			boolean hasDiferent = false;
			for( int c = 0; c < table.getCols(); c++ ) {
				if( !p.equals( plays[r][c]  ) ) hasDiferent = true; 
			}
			if( !hasDiferent ) return true;
		}
		return false;
	}
	
	
	
	public boolean isADraw() {
		
		boolean finded = false;
		for( int i = 0; i < table.getRows(); i++ ) {
			for( int j = 0; j < table.getCols(); j++ ) {
				if( ! filled[i][j] ) finded = true;
			}
		}
		
		return !finded;
		
	}
	
	
	
	public void winner( Player p ){
		System.out.println( "Vencedor : " + p.getName() );
		System.exit(0);
	}
	
	
	
	public Table getTable() {
		return this.table;
	}
	
	
	
	public Player[][] getPlays(){
		return this.plays;
	}
	
	public Player[] getPlayers(){
		return this.players;
	}
	
	public boolean canSetPlay( int row, int col ) {
		return !this.filled[row][col];
	}
	
	
	
	public void setPlay( int row, int col, Player p ) {
		this.filled[row][col] = true;
		this.plays[row][col] = p;
	}
	
	

}

