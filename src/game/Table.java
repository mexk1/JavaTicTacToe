package game;

public class Table{
	
	private int rows = 3;
	private int cols = 3;
	
	public static void main( String[] args ) {
		
	}
	
	public void render( Game g ) {
		System.out.println( "Render" );
		for( int i = 0; i < rows; i++ ) {
			for( int j = 0; j < cols; j++ ) {
				if( !g.canSetPlay(i, j) ) {
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
					System.out.print( " | " );
				}
				
				if( j == cols - 1 ) {
					if( i != rows - 1 ) {
						System.out.println( "---------------------" );				
					}
				}							
			}
			
		}
		System.out.println( "" );
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
}
