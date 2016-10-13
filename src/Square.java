import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Parent class for the squares on the board
 * @author jburge
 *
 */
public abstract class Square {
	

	protected Board board;
	
	private ArrayList<Player> playersOn = new ArrayList<Player>();
	
	
	public Square() {
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	
	
	public abstract void doAction();
	
	public abstract String getLabel();
	
	public void addPlayer(Player playa) {
		playersOn.add(playa);
	}
	
	public void removePlayer(Player playa) {
		playersOn.remove(playa);
	}

	
	public ArrayList<Player> getPlayersOn() {
		return playersOn;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.playersOn = players;
	}
	
	
	
}
