package game;

public class Table{
	
	private int rows = 3;
	private int cols = 3;
	
	public static void main( String[] args ) {
		
	}
	
	public void render( Game g ) {
		//Renderiza a tabela no console
		//Render the table in the console
		
		System.out.println( "" );
		for( int i = 0; i < rows; i++ ) {
			for( int j = 0; j < cols; j++ ) {
				if( !g.canSetPlay(i, j) ) {
					//Se é um lugar preenchido, pega a mark do jogador
					//If is a filled position, get the player mark
					char c = g.getPlays()[i][j].getMark();
					if( j == cols - 1 ) {
						System.out.println( "  " + c + "  " );	
					}else {
						System.out.print( "  " + c + "  " );	
					}
				}else{
					if( j == cols - 1 ) {
						System.out.println( "  -  " );	
					}else {
						System.out.print( "  -  " );	
					}
				}
				
				if( j != cols - 1 ) {
					//Separador de colunas
					//Collumn Separator
					System.out.print( " | " );
				}
				
				if( j == cols - 1 ) {
					//Separador de linhas
					//Line Separator
					if( i != rows - 1 ) {
						System.out.println( "---------------------" );				
					}
				}							
			}
			
		}
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
}
