/**
 * This class extends represents a go back square.
 * 
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */
public class GoBackSquare extends Square {

	/**
	 * Constructor for go back square. Does not take in any parameters.
	 */
	public GoBackSquare() {
	}

	/**
	 * Action for go back square. Player moves back one space.
	 */
	@Override
	public void doAction() {
		board.setAnsweredCorrectly(false);
		board.goBack();
		board.next();
	}

	/**
	 * @return String of the square label - "Go Back"
	 */
	@Override
	public String getLabel() {
		return "Go Back";
	}

}
