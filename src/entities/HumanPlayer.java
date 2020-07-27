package entities;

import java.util.Scanner;

import game.Game;

public class HumanPlayer extends Player{
	
	private char mark = 'X';
	private Scanner scanner;
	
	public HumanPlayer(String name) {
		super(name);
		scanner = new Scanner( System.in );
	}

	public static void main( String[] args ) {
		
	}
	
	private int chooseCol() {
		System.out.println("Sua vez, digite a coluna: 1 - 3");
		int col = scanner.nextInt() - 1;
		if( col > 2 || col < 0 ) {
			System.out.println("Jogada invalida, exolha entre 1 e 3");
			return chooseCol();
		}
		return col;
	}
	
	private int chooseRow() {
		System.out.println("Sua vez, digite a linha: 1 - 3");
		int row = scanner.nextInt() - 1;		
		if( row > 2 || row < 0 ) {
			System.out.println("Jogada invalida, exolha entre 1 e 3");
			return chooseRow();
		}
		return row;
	}
	
	public void play( Game game ) {
		int row = chooseRow();
		int col = chooseCol();
		
		if( game.canSetPlay(row, col) ) {
			game.setPlay( row, col, this );
			game.Next();
		}else {
			System.out.println("Jogada invalida, lugar preenchido");
			play( game );
		}
		
	}
	

	public char getMark() {
		return this.mark;
	}
}
