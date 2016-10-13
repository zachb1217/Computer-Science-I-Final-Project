
public class ShuffleSquare extends Square {

	public ShuffleSquare() {
		
	}

	@Override
	public void doAction() {
		
		board.shuffle();
		board.setAnsweredCorrectly(false);
		board.setShuffle(true);
		
		
		board.next();
		
		
	}

	@Override
	public String getLabel() {
		
		return "Shuffle";
	}

}
