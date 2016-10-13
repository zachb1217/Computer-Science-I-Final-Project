import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This defines the class for a plain (blank) square
 * 
 * @author jburge
 * @author zberkowitz
 * @author astone
 * @author agandhi
 */
public class PlainSquare extends Square {

	private String[] truefalse = { "False", "True" };
	private ImageIcon adi = new ImageIcon(getClass().getResource("Adi.jpg"));
	private ImageIcon adiApproves = new ImageIcon(getClass().getResource("AdiApproves.jpg"));
	private ImageIcon adiDoesNot = new ImageIcon(getClass().getResource("AdiDoesNot.jpg"));

	/**
	 * Constructor for a plain square. Does not take in any parameters.
	 */
	public PlainSquare() {
	}

	/**
	 * When a player lands on a blank square, they are prompted a question. If
	 * the answer is correct, they receive a message saying they were correct
	 * (and move forward). If the answer is incorrect, they receive a message
	 * saying they were incorrect (and move backwards).
	 */
	public void doAction() {

		Question temp = board.newQuestion();

		int choice = JOptionPane.showOptionDialog(null, temp.getPrompt(), "Question", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, adi, truefalse, "True");
		Boolean choiceBool;
		if (choice == 0) {
			choiceBool = false;
		} else
			choiceBool = true;

		if (choiceBool == temp.getAnswer()) {
			board.setAnsweredCorrectly(true);
			JOptionPane.showMessageDialog(null, "Correct! (You move forward one square)", "Adi Approves",
					JOptionPane.INFORMATION_MESSAGE, adiApproves);
		} else {
			board.wrongAnswer();
			JOptionPane.showMessageDialog(null, "Adi is not impressed...", "Incorrect", JOptionPane.INFORMATION_MESSAGE,
					adiDoesNot);
			board.setAnsweredCorrectly(false);
			board.next();
		}

	}

	/**
	 * @return String - returns plain square label (blank)
	 */
	public String getLabel() {
		return "";
	}

}
