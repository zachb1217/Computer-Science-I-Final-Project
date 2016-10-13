import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class defines the game board
 * 
 * @author jburge
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */
public class Board {

	private ArrayList<Question> questions = new ArrayList<Question>();
	private Random randy = new Random();

	public static final int BOARDSIZE = 9;
	public static final int FINALSQUARE = 32;

	/**
	 * An array list of all the squares on our board
	 */
	private ArrayList<Square> board = new ArrayList<Square>();
	/**
	 * The people playing the game
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	/**
	 * The person whose turn it is
	 */
	private int playerTurn = 0;

	private boolean shuffle = false;

	private boolean answeredCorrectly = false;

	/**
	 * Constructor for the board game
	 * 
	 * @param ArrayList<Player>
	 *            playerNames Adds questions to ArrayList<Question> questions
	 */
	public Board(ArrayList<Player> p) {
		players.addAll(p);

		questions.add(new Question("Adi loves steak.", false));
		questions.add(new Question("Adi's favorite color is chartreuse.", false));
		questions.add(new Question("Adi has 3 cats.", false));
		questions.add(new Question("Aditya means rising sun.", true));
		questions.add(new Question("Adi likes chocolate.", true));
		questions.add(new Question("Adi can wiggle both ears and eyebrows individually.", true));
		questions.add(new Question("Adi has a forked tongue.", false));
		questions.add(new Question("Adi has 2 cats", false));
		questions.add(new Question("Adi has 1 cat.", false));
		questions.add(new Question("Adi is allergic to cats.", true));
		questions.add(new Question("Adi likes country music.", false));
		questions.add(new Question("Adi's favorite number is 4.", true));
		questions.add(new Question("This game was Adi's idea.", false));
		questions.add(new Question("Adi loves long walks on the beach.", true));
		questions.add(new Question("Adi has played the piano for 15 years.", true));
		questions.add(new Question("Adi is 19 years old.", true));
		questions.add(new Question("Adi likes Justin Bieber.", true));
		questions.add(new Question("Adi likes to ski.", true));
		questions.add(new Question("Adi is from Boston.", false));
		questions.add(new Question("Adi wears glasses.", true));
		questions.add(new Question("Adi likes the mountains.", false));
		questions.add(new Question("Adi plays ultimate frisbee.", true));
		questions.add(new Question("Adi has a rugged exterior but is soft on the inside.", false));
		questions.add(new Question("Adi likes the smell of roses.", false));
		questions.add(new Question("Adi likes pumpkin spice lattes.", true));
		questions.add(new Question("Adi is six feet tall.", false));
		questions.add(new Question("Adi is a Chemistry major.", true));
		questions.add(new Question("Adi does not know how to whistle.", true));
		questions.add(new Question("Adi enjoys wearing pastel colors.", true));
		questions.add(new Question("Adi's dream profession is to be a pirate.", true));
		questions.add(new Question("Adi likes cheese.", true));
		questions.add(new Question("Adi has a serious addiction to coffee.", true));
		questions.add(new Question("This game is fun.", false));
		questions.add(new Question("Adi loves goats.", true));
		questions.add(new Question("BAAAAAAAAAAAAA.", true));

	}

	/**
	 * Randomly re-order the squares (except for the start and end, of course)
	 */
	public void shuffle() {
		Collections.shuffle(board);

		for (Square tile : board) {
			if (tile instanceof StartSquare) {
				board.set(board.indexOf(tile), board.get(0));
				board.set(0, tile);
			}
		}

		for (Square tile : board) {
			if (tile instanceof FinishSquare) {
				board.set(board.indexOf(tile), board.get(FINALSQUARE));
				board.set(FINALSQUARE, tile);
			}
		}

	}

	/**
	 * Goes through questions and returns a random question
	 * 
	 * @return Question temp
	 */
	public Question newQuestion() {
		Question temp = questions.get(randy.nextInt(questions.size()));
		questions.remove(temp);
		return temp;
	}

	/**
	 * Move a player back to the last save point square they landed on or to the
	 * start
	 */
	public void goBack() {
		int playerCoord = findPlayer(players.get(playerTurn));
		board.get(playerCoord).removePlayer(players.get(playerTurn));

		int newPlayerCoord;

		if (players.get(playerTurn).getSavePoints().isEmpty() == true) {
			newPlayerCoord = 0;
		} else {
			newPlayerCoord = board.indexOf(players.get(playerTurn).getTopSavePoint());
			players.get(playerTurn).removeTopSavePoint();
		}

		board.get(newPlayerCoord).addPlayer(players.get(playerTurn));

	}

	/**
	 * Add a new square to the board. If it's the first one, add the players to
	 * it
	 * @param newSquare - the square being added
	 * 
	 */
	public void addSquare(Square newSquare) {
		board.add(newSquare);
		newSquare.setBoard(this);
		if (board.size() == 1) {
			for (int i = 0; i < players.size(); i++)
				newSquare.getPlayersOn().add(players.get(i));
		}
	}

	/**
	 * Creates our game board
	 * 
	 * @return - a 2-D array of all the squares on the board
	 */
	public Square[][] createBoard() {
		Square[][] result = new Square[BOARDSIZE][BOARDSIZE];
		for (int x = 0; x < BOARDSIZE; ++x) {
			for (int y = 0; y < BOARDSIZE; ++y) {
				int position = mapSquareToPosition(x, y);
				result[x][y] = null;
				if ((position != -1) && (position < board.size())) {
					result[x][y] = board.get(position);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param Player playa
	 * @return int (represents the square the player is on)
	 */
	public int findPlayer(Player playa) {
		for (Square s : board) {
			if (s.getPlayersOn().contains(playa))
				return board.indexOf(s);
		}
		return -1;

	}

	/**
	 * Move the player to their new position based on the dice roll
	 * 
	 * @param value - the dice roll
	 */

	public void doMove(int value) {
		players.get(playerTurn).setPosition(players.get(playerTurn).getPosition() + value);

		int playerCoord = findPlayer(players.get(playerTurn));
		board.get(playerCoord).removePlayer(players.get(playerTurn));
		int newPlayerCoord = playerCoord + value;

		if (newPlayerCoord > FINALSQUARE)
			newPlayerCoord = FINALSQUARE;

		board.get(newPlayerCoord).addPlayer(players.get(playerTurn));

	}
	
	/**
	 * Moves the player back one space if they get the question wrong.
	 */

	public void wrongAnswer() {
		players.get(playerTurn).setPosition(players.get(playerTurn).getPosition() - 1);

		int playerCoord = findPlayer(players.get(playerTurn));
		board.get(playerCoord).removePlayer(players.get(playerTurn));
		int newPlayerCoord = playerCoord - 1;

		board.get(newPlayerCoord).addPlayer(players.get(playerTurn));

	}

	/**
	 * Go to the next player's turn
	 */
	public void next() {
		if (playerTurn == players.size() - 1)
			playerTurn = 0;
		else
			playerTurn++;

	}

	/**
	 * Gets the square the player is on after moving.
	 * @return Square the player is on
	 */
	public Square actionSquare() {
		int coord = findPlayer(players.get(playerTurn));

		return board.get(coord);
	}

	/**
	 * Figure out the correspondence between the square number (in the array
	 * list) and its physical location on the 9x9 board
	 * 
	 * @param x - x-coordinate in the array
	 * @param y - y-coordinate in the array
	 * @return - the index of the square in our array list
	 */
	private int mapSquareToPosition(int x, int y) {
		if ((x < 0) || (y < 0) || (x >= BOARDSIZE) || (y >= BOARDSIZE))
			throw new IllegalArgumentException("(x,y) invalid");
		// If y=0, we are looking at the bottom row of the grid.
		// The index in the array corresponds to the X coordinate.
		if (y == 0) {
			return x;
		}
		// Lower vertical set of squares - at Max x-coordinate
		else if (y < BOARDSIZE / 2) {
			if (x == BOARDSIZE - 1) {
				return BOARDSIZE + y - 1;
			} else
				return -1;
		}
		// The set that goes across the middle of the board
		else if (y == BOARDSIZE / 2) {
			return (2 * BOARDSIZE + (BOARDSIZE / 2) - x - 2);
		}
		// The squares going up the left side
		else if (y < BOARDSIZE - 1) {
			if (x == 0) {
				return (2 * BOARDSIZE + y - 2);
			}
		}
		// The squares along the top
		else if (y == BOARDSIZE - 1) {
			return (3 * BOARDSIZE) - 3 + x;
		}
		return -1;

	}

	/**
	 * Checks whether or not there is a player on the final square.
	 * @return true if a player is on the final square
	 */
	public boolean gameOver() {
		for (Player playa : players) {
			if (findPlayer(playa) == FINALSQUARE)
				return true;

		}
		return false;

	}
	
	//getters and setters

	public boolean getShuffle() {
		return shuffle;
	}

	public void setShuffle(Boolean booly) {
		shuffle = booly;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public ArrayList<Square> getGameBoard() {
		return board;
	}

	public void setGameBoard(ArrayList<Square> board) {
		this.board = board;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setAnsweredCorrectly(boolean answeredCorrectly) {
		this.answeredCorrectly = answeredCorrectly;
	}

	public boolean isAnsweredCorrectly() {
		return answeredCorrectly;
	}

}
