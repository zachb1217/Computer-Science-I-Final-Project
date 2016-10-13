import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * This class displays the game initialization window, where players can
 * customize their game (number of each type of square, player names,
 * instructions, see player statistics, and start the game).
 * 
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */
public class GameInitialization extends JFrame implements ActionListener {

	private Game ourGame;

	private static ArrayList<Player> players = new ArrayList<Player>();

	private static final int SQUARETOTAL = 31;
	private static final int DEFAULTSHUFFLE = 2;
	private static final int DEFAULTGOBACK = 5;
	private static final int DEFAULTROLLAGAIN = 3;
	private static final int DEFAULTSAVE = 6;

	private JPanel gameMenu;
	private JComboBox playerOptions;
	private JButton addPlayer = new JButton("Select Player");
	private JButton createPlayer = new JButton("Create Player");
	private JLabel numShuffleLabel = new JLabel("Enter # of Shuffle Squares:");
	private JLabel numGoBackLabel = new JLabel("Enter # of Go Back Squares:");
	private JLabel numSaveLabel = new JLabel("Enter # of Save Squares:");
	private JLabel numRollAgainLabel = new JLabel("Enter # of Roll Again Squares:");
	private JTextField numShuffle = new JTextField("");
	private JTextField numGoBack = new JTextField("");
	private JTextField numSave = new JTextField("");
	private JTextField numRollAgain = new JTextField("");
	private JButton set = new JButton("Set");
	private JLabel player1 = new JLabel("Player 1");
	private JLabel player2 = new JLabel("Player 2");
	private JLabel player3 = new JLabel("Player 3");
	private JLabel player4 = new JLabel("Player 4");
	private JButton start = new JButton("Start Game");

	private JButton stats = new JButton("Player Stats");

	private JButton howTo = new JButton("Instructions");

	int playerNumber = 1;

	int goBackInt = DEFAULTGOBACK;
	int shuffleInt = DEFAULTSHUFFLE;
	int rollAgainInt = DEFAULTROLLAGAIN;
	int savePointInt = DEFAULTSAVE;

	/**
	 * Constructor for the Game Initialization. Defines the window for the game
	 * menu.
	 */
	public GameInitialization() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Game Settings");

		this.setResizable(false);

		playerOptions = new JComboBox<String>();

		for (Player playa : players) {
			playerOptions.addItem(playa.getPlayerName());
		}

		addPlayer.addActionListener(this);
		addPlayer.setActionCommand("Add Player");

		createPlayer.addActionListener(this);
		createPlayer.setActionCommand("Create Player");

		set.addActionListener(this);
		set.setActionCommand("Set");

		start.addActionListener(this);
		start.setActionCommand("Start Game");

		stats.addActionListener(this);
		stats.setActionCommand("Stats");

		howTo.addActionListener(this);
		howTo.setActionCommand("Instructions");

		gameMenu = new JPanel();
		gameMenu.setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(1, 2));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(4, 1));

		JLabel choosePlayer = new JLabel("Choose Player: ");
		choosePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		leftPanel.add(choosePlayer);

		leftPanel.add(playerOptions);
		leftPanel.add(addPlayer);
		leftPanel.add(createPlayer);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		JPanel rightNorth = new JPanel();

		JPanel rightSouth = new JPanel();

		JPanel rightCenter = new JPanel();
		rightCenter.setLayout(new GridLayout(4, 2));

		JLabel setSquares = new JLabel("Set Squares: ");
		setSquares.setHorizontalAlignment(SwingConstants.CENTER);

		numSave.setHorizontalAlignment(JTextField.CENTER);
		numGoBack.setHorizontalAlignment(JTextField.CENTER);
		numRollAgain.setHorizontalAlignment(JTextField.CENTER);
		numShuffle.setHorizontalAlignment(JTextField.CENTER);

		rightPanel.add(setSquares, BorderLayout.NORTH);
		rightCenter.add(numSaveLabel);
		rightCenter.add(numSave);
		rightCenter.add(numGoBackLabel);
		rightCenter.add(numGoBack);
		rightCenter.add(numRollAgainLabel);
		rightCenter.add(numRollAgain);
		rightCenter.add(numShuffleLabel);
		rightCenter.add(numShuffle);

		rightPanel.add(rightCenter, BorderLayout.CENTER);
		rightPanel.add(set, BorderLayout.SOUTH);

		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 1));

		JPanel southTop = new JPanel();
		southTop.setLayout(new GridLayout(1, 4));
		player1.setHorizontalAlignment(SwingConstants.CENTER);
		player2.setHorizontalAlignment(SwingConstants.CENTER);
		player3.setHorizontalAlignment(SwingConstants.CENTER);
		player4.setHorizontalAlignment(SwingConstants.CENTER);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		player1.setBorder(blackline);
		player2.setBorder(blackline);
		player3.setBorder(blackline);
		player4.setBorder(blackline);

		southTop.add(player1);
		southTop.add(player2);
		southTop.add(player3);
		southTop.add(player4);

		southPanel.add(southTop);
		southPanel.add(start);

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(1, 1));

		westPanel.add(stats);

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(1, 1));
		eastPanel.add(howTo);

		gameMenu.add(eastPanel, BorderLayout.EAST);
		gameMenu.add(westPanel, BorderLayout.WEST);
		gameMenu.add(centerPanel, BorderLayout.CENTER);
		gameMenu.add(southPanel, BorderLayout.SOUTH);

		this.add(gameMenu);

	}

	/**
	 * Main for the Game Initialization window.
	 */
	public static void main(String[] args) {
		GameInitialization newGame = new GameInitialization();
		newGame.pack();
		newGame.setLocationRelativeTo(null);
		newGame.setVisible(true);
	}

	/**
	 * Responds to the user creating a player and selecting players for the game
	 * (supports up to four players). Responds to the "set" button by setting
	 * the number of each type of square entered by the user. Also opens windows
	 * for game statistics and instructions. Finally, allows user to start the
	 * game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Add Player")) {
			if (playerOptions.getItemCount() == 0) {
				JOptionPane.showMessageDialog(null, "No Players Yet!");
			} else {
				if (playerNumber == 1) {
					String player1Name = (String) playerOptions.getSelectedItem();
					player1.setText(player1Name);
					playerNumber++;
				} else if (playerNumber == 2) {
					String player2Name = (String) playerOptions.getSelectedItem();
					if (player2Name.equals(player1.getText())) {
						JOptionPane.showMessageDialog(null, "Can't have two of the same player!");
					}

					else {
						player2.setText(player2Name);
						playerNumber++;
					}

				} else if (playerNumber == 3) {
					String player3Name = (String) playerOptions.getSelectedItem();

					if (player3Name.equals(player1.getText()) || player3Name.equals(player2.getText())) {
						JOptionPane.showMessageDialog(null, "Can't have two of the same player!");
					}

					else {
						player3.setText(player3Name);
						playerNumber++;
					}
				} else if (playerNumber == 4) {
					String player4Name = (String) playerOptions.getSelectedItem();
					if (player4Name.equals(player1.getText()) || player4Name.equals(player2.getText())
							|| player4Name.equals(player3.getText())) {
						JOptionPane.showMessageDialog(null, "Can't have two of the same player!");
					}

					else {
						player4.setText(player4Name);
						playerNumber++;
					}
				}
			}
		} else if (e.getActionCommand().equals("Create Player")) {
			String newPlayer = JOptionPane.showInputDialog("Input Player Name: ");
			if (!newPlayer.isEmpty()) {
				players.add(new Player(newPlayer));
				playerOptions.addItem(newPlayer);
			}

		} else if (e.getActionCommand().equals("Set")) {
			try {

				goBackInt = Integer.parseInt(numGoBack.getText());
				shuffleInt = Integer.parseInt(numShuffle.getText());
				rollAgainInt = Integer.parseInt(numRollAgain.getText());
				savePointInt = Integer.parseInt(numSave.getText());

				if (goBackInt < 0 || shuffleInt < 0 || rollAgainInt < 0 || savePointInt < 0) {
					goBackInt = DEFAULTGOBACK;
					shuffleInt = DEFAULTSHUFFLE;
					rollAgainInt = DEFAULTROLLAGAIN;
					savePointInt = DEFAULTSAVE;
					JOptionPane.showMessageDialog(null, "Cannot input a negative number!");
				} else if (goBackInt + shuffleInt + rollAgainInt + savePointInt <= SQUARETOTAL) {

					JOptionPane.showMessageDialog(null, "Squares Set!");

				} else {
					JOptionPane.showMessageDialog(null, "Total number of squares must be less than or equal to 31!");
					goBackInt = DEFAULTGOBACK;
					shuffleInt = DEFAULTSHUFFLE;
					rollAgainInt = DEFAULTROLLAGAIN;
					savePointInt = DEFAULTSAVE;

					numGoBack.setText("");
					numShuffle.setText("");
					numRollAgain.setText("");
					numSave.setText("");
				}
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "Must enter a number!");
				numGoBack.setText("");
				numShuffle.setText("");
				numRollAgain.setText("");
				numSave.setText("");
				goBackInt = DEFAULTGOBACK;
				shuffleInt = DEFAULTSHUFFLE;
				rollAgainInt = DEFAULTROLLAGAIN;
				savePointInt = DEFAULTSAVE;
			}

		} else if (e.getActionCommand().equals("Start Game")) {
			ArrayList<String> playerNames = new ArrayList<String>();

			if (player1.getText() != "Player 1")
				playerNames.add(player1.getText());

			if (player2.getText() != "Player 2")
				playerNames.add(player2.getText());

			if (player3.getText() != "Player 3")
				playerNames.add(player3.getText());

			if (player4.getText() != "Player 4")
				playerNames.add(player4.getText());

			String[] names = new String[playerNames.size()];
			for (int i = 0; i < names.length; i++) {
				names[i] = playerNames.get(i);
			}

			if (names.length >= 2) {

				ourGame = new Game(this, shuffleInt, goBackInt, savePointInt, rollAgainInt);
				ourGame.setVisible(true);
				this.dispose();
			} else
				JOptionPane.showMessageDialog(null, "Must have at least two players!");
		} else if (e.getActionCommand().equals("Instructions")) {
			try {
				Scanner in = new Scanner(new FileInputStream("Instructions.txt"));
				String howTo = "";
				while (in.hasNextLine()) {
					howTo += in.nextLine() + "\n";
				}

				JOptionPane.showMessageDialog(null, howTo);
			} catch (Exception g) {

			}
		} else if (e.getActionCommand().equals("Stats")) {
			StatsGUI stat = new StatsGUI(this);
			stat.pack();
			stat.setVisible(true);
		}

	}

	public ArrayList<Player> getPlayers() {
		return players;

	}

}
