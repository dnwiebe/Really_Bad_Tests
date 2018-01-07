
public class CheckerBoard {

	private Boolean[][] boardData = new Boolean[8][8];

	public void placePieceAt (int x, int y, Boolean isRed) {
		boardData[x][y] = isRed;
	}

	public Boolean checkPieceAt (int x, int y) {
		return boardData[x][y];
	}
}











//------

@Test
public void checkPieceReadsPlacePiece () {
	Checkerboard subject = new Checkerboard ();
	subject.placePieceAt (5, 5, Boolean.TRUE);

	Boolean result = subject.checkPieceAt (5, 5);

	assertEquals (Boolean.TRUE, result);
}














//------

public class CheckerBoard {

	private Boolean[][] boardData = new Boolean[8][8];

	public void placePieceAt (int x, int y, Boolean isRed) {
		boardData[y][x] = isRed;
	}

	public Boolean checkPieceAt (int x, int y) {
		return boardData[x][y];
	}
}











//-----

@Test
public void checkPieceReadsPlacePiece () {
	Checkerboard subject = new Checkerboard ();
	subject.placePieceAt (3, 5, true);

	Boolean result = subject.checkPieceAt (3, 5);

	assertEquals (Boolean.TRUE, result);
}














//------
/*

Morals: 

1. Use test-data values that are different from each other when you can.



















*/