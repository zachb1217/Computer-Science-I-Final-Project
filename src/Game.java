import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main frame for the Board Game.
 * 
 * @author jburge
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */
public class Game extends JFrame implements ActionListener {

	private Board board;
	public static final int PLAYINGSQUARES = 31;
	public static final int DICEMAX = 6;

	private Random randPaul; // used to roll our dice
	private GameBoardPanel gbp;

	private JButton roll = new JButton("Roll");
	private JLabel dialog = new JLabel();

	/**
	 * Constructor for the Game - creates the initial board and its squares;
	 * then creates the GameBoardPanel that will display them. This method also
	 * shuffles all squares each time the game is started (randomized
	 * positions).
	 * 
	 * @param int
	 *            numShuffle - number of shuffle squares
	 * @param int
	 *            numGoBack - number of go back squares
	 * @param int
	 *            numSavePoint - number of save point squares
	 * @param int
	 *            numRollAgain - number of roll again squares
	 */
	public Game(GameInitialization g, int numShuffle, int numGoBack, int numSavePoint, int numRollAgain) {

		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Board Game");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		board = new Board(g.getPlayers());

		board.addSquare(new StartSquare());

		int numPlain = PLAYINGSQUARES - numShuffle - numGoBack - numSavePoint - numRollAgain;

		for (int i = 0; i < numShuffle; i++)
			board.addSquare(new ShuffleSquare());
		for (int i = 0; i < numGoBack; i++)
			board.addSquare(new GoBackSquare());
		for (int i = 0; i < numRollAgain; i++)
			board.addSquare(new RollAgainSquare());
		for (int i = 0; i < numSavePoint; i++)
			board.addSquare(new SaveSquare());
		for (int i = 0; i < numPlain; i++)
			board.addSquare(new PlainSquare());

		board.addSquare(new FinishSquare());

		// Shuffle our board so the order is random
		board.shuffle();
		gbp = new GameBoardPanel(board);
		this.add(gbp, BorderLayout.CENTER);
		this.add(makeControl(), BorderLayout.SOUTH);
		randPaul = new Random(); // used when rolling dice
	}

	/**
	 * Sets up the panel at the bottom that shows whose turn it is and lets the
	 * player roll the dice
	 * 
	 * @return control - the control JPanel of the game
	 */
	private JPanel makeControl() {
		JPanel control = new JPanel();
		control.setLayout(new BorderLayout());
		control.add(roll, BorderLayout.WEST);
		roll.addActionListener(this);
		roll.setActionCommand("Roll");

		String dialogMessage = "It is " + this.getBoard().getPlayers().get(0).getPlayerName() + "'s turn!";
		dialog.setText(dialogMessage);
		dialog.setHorizontalAlignment(JLabel.CENTER);
		control.add(dialog, BorderLayout.CENTER);
		return control;

	}

	/**
	 * This method will respond to the Roll button. The player will move a
	 * number of spaces corresponding to the number on the dice. It will
	 * consider if a player has won and will also ask if they would like to play
	 * again. Finally, it will notify the next person that it is their turn (all
	 * upon clicking the roll button).
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Roll")) {
			if (board.gameOver() == false) {
				int roll = randPaul.nextInt(DICEMAX) + 1;
				String rollMessage = "You rolled a " + roll;
				JOptionPane.showMessageDialog(null, rollMessage);

				board.doMove(roll);

				gbp.update();

				board.actionSquare().doAction();

				while (board.isAnsweredCorrectly()) {
					board.doMove(1);

					gbp.update();

					board.actionSquare().doAction();

				}

				if (board.getShuffle() == true) {

					this.remove(gbp);
					gbp = new GameBoardPanel(board);
					this.add(gbp);
					board.setShuffle(false);
				}

				gbp.update();

				if (board.gameOver() == true) {
					String winnerMessage = getBoard().getPlayers().get(board.getPlayerTurn()).getPlayerName()
							+ " Wins!!";
					dialog.setText(winnerMessage);
					JOptionPane.showMessageDialog(null, board.getPlayers().get(board.getPlayerTurn()).getPlayerName()
							+ " has won Adi's affection!");

					board.getPlayers().get(board.getPlayerTurn()).gamePlayed();
					board.getPlayers().get(board.getPlayerTurn()).oneWin();

					int playagain = JOptionPane.showOptionDialog(null, "Would you like to play again?", "Game Over",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "No", "Yes" },
							"Yes");

					if (playagain == 1) {

						GameInitialization newGame = new GameInitialization();
						newGame.pack();
						newGame.setVisible(true);
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Thanks for Playing!");
						System.exit(0);
					}
				}

				else {
					String dialogMessage = "It is " + getBoard().getPlayers().get(board.getPlayerTurn()).getPlayerName()
							+ "'s turn!";
					dialog.setText(dialogMessage);
				}

			}
		}

	}

	// getters and setters

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
