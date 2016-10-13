
public class SaveSquare extends Square {
	
	private static int squareNumber = 0;
	
	public SaveSquare() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		board.setAnsweredCorrectly(false);
		board.getPlayers().get(board.getPlayerTurn()).addSavePoint(this);
		board.next();
		
	}

	@Override
	public String getLabel() {
		int numSave = 0;
		
		for(Square s: board.getGameBoard()) {
			if(s instanceof SaveSquare)
				numSave++;
		}
		
		squareNumber++;
		
		if(squareNumber > numSave)
			squareNumber = 1;
	
		return "Save Point " + squareNumber;
		
	}

}
