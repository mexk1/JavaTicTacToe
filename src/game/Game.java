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
		
		//Faz o pre jogo, pegando o nome do jogador 
		// Do the pre game, retrieve the player name
		this.preGame();
		

		//Gera o tabuleiro
		// Generate the table
		this.generateTable();

		//Inicia o jogo 
		// Starts the game		
		this.run();
	}
		
	
	public static void main( String[] args ) {
		//Começa um novo jogo - Starts a new game
		new Game();
	}	

	private void run() {
		//Inicia as variaveis 
		// Init the variables 
		this.plays = new Player[table.getRows()][table.getRows()];
		this.filled = new boolean[table.getRows()][table.getRows()];
		this.players = new Player[2];
		
		//Inicia todas as posições como nao preenchidas 
		// Starts all the positions as not filled
		for( int i = 0; i < table.getRows(); i++ ) {
			for( int j = 0; j < table.getCols(); j++ ) {
				filled[i][j] = false;
			}
		}
		
		//Renderiza o tabuleiro no console
		//Render the table in console
		this.table.render( this );
		
		//Aleatoriamente seleciona um jogador para iniciar
		//Randomly select a player to start 
		int x = (int) Math.round( Math.random() );
		currentPlayer = players[x];
		
		//Faz o primeiro jogador jogar
		//Make the first player play
		currentPlayer.play( this );
	}
	
	
	
	private void preGame() {

		//Pega o nome do usuario e instancia um Player com este nome;
		//Get the user name and instantiate a Player with this name;
		System.out.println( "Digite seu nome" );
		String name = scanner.nextLine();
		this.players[0] = new HumanPlayer( name );
		
		//Escolher a dificuldade;
		//Choose the difficulty;
		this.chooseDifficulty();
		
		
		System.out.println( "Voce é o X" );
	}
	
	
	
	private void chooseDifficulty() {

		//Escolher a dificuldade;
		//Choose the difficulty;
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
		//Renderiza o tabuleiro no console
		//Render the table in console
		this.table.render( this );
		
		//Verifica um resultado
		//Check for a result
		if( this.checkWinner() ) { 
			//Se venceu
			//If is a Win
			this.winner( currentPlayer );
		}else if( isADraw() ){
			//Se Empatou
			//if is a draw
			System.out.println("Empatou");
			System.exit(0);
		}else {
			//Muda para o proximo jogador;
			//Change to next player;
			if( currentPlayer.equals( players[0] ) ) {
				currentPlayer = players[1];
			}else {
				currentPlayer = players[0];
			}
			
			//Faz o jogar jogar
			//Make the player play
			currentPlayer.play( this );
		}
	}
	
	
	public boolean checkWinner() {
		//Verifica se ha vencedor
		//Check if has a winner
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
		//Verifica se ha vencedor nas diagonais
		//Check if has a winner in the diagonals
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
		//Verifica se ha vencedor nas colunas
		//Check if has a winner in the collumns
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
		//Verifica se ha vencedor nas linhas
		//Check if has a winner in the rows
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
		//Verifica se é um empate
		//Check if are a draw
		boolean finded = false;
		for( int i = 0; i < table.getRows(); i++ ) {
			for( int j = 0; j < table.getCols(); j++ ) {
				if( ! filled[i][j] ) finded = true;
			}
		}
		
		return !finded;
		
	}
	
	
	
	public void winner( Player p ){
		//Mostra mensagem de vencedor
		//Show winners message
		System.out.println( "Vencedor : " + p.getName() );
		System.exit(0);
	}
	
	
	
	public Table getTable() {
		//Pega o tabuleiro
		//Retrieve the table
		return this.table;
	}
	
	
	
	public Player[][] getPlays(){
		//Pega as jogadas
		//Retrieve the plays
		return this.plays;
	}
	
	
	
	public Player[] getPlayers(){
		//Pega as jogadores
		//Retrieve the players
		return this.players;
	}
	
	public boolean canSetPlay( int row, int col ) {
		//Verifica se a posição esta vazia
		//Check if the position are empty
		return !this.filled[row][col];
	}
	
	
	
	public void setPlay( int row, int col, Player p ) {
		//Faz a jogada e marca nas respectivas variaveis
		//Do the play and mark the variables
		this.filled[row][col] = true;
		this.plays[row][col] = p;
	}
	
	

}

