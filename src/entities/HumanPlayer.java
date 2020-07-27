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
		//Faz o usuário escolher uma coluna
		//Make the user choose one column
		System.out.println("Sua vez, digite a coluna: 1 - 3");
		int col = scanner.nextInt() - 1;
		if( col > 2 || col < 0 ) {
			System.out.println("Jogada invalida, exolha entre 1 e 3");
			return chooseCol();
		}
		return col;
	}
	
	private int chooseRow() {
		//Faz o usuário escolher uma linha
		//Make the user choose one row
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
		
		//Verifica se a jogade é valida e entao a faz
		//Check if is a valid play, so then do the play
		if( game.canSetPlay(row, col) ) {
			game.setPlay( row, col, this );
			game.Next();
		}else {
			System.out.println("Jogada invalida, lugar preenchido");
			play( game );
		}
		
	}
	
	public char getMark() {
		//Pega a marca pra preencher o tabuleiro
		//Retrieve the mark to fill the table
		return this.mark;
	}
}
