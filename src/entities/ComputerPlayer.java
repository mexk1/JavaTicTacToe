package entities;

public abstract class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
	}

	public char mark = 'O';
	
	public char getMark() {
		//Pega a marca pra preencher o tabuleiro
		//Retrieve the mark to fill the table
		return this.mark;
	}
}
