import javax.swing.JOptionPane;

/**
 * This class extends Square and represents the final square of the game.
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */

public class FinishSquare extends Square {

	/**
	 * Constructor for the finish square. Does not take in any parameters.
	 */
	public FinishSquare() {
	}

	/**
	 * Does not ask the player a question if they are on the final square.
	 */
	@Override
	public void doAction() {
		board.setAnsweredCorrectly(false);
	}

	/**
	 * @return the label of the square "Finish"
	 */
	@Override
	public String getLabel() {
		return "Finish";
	}

}
