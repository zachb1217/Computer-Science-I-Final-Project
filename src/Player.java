import java.util.ArrayList;

public class Player {
	private String playerName;
	private ArrayList<Square> savePoints;
	private int position = 0;
	private int wins = 0;
	private int gamesPlayed = 0;
	


	//Sets playerName
	public Player(String name) {
		playerName = name;
		savePoints = new ArrayList<Square>();
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String newplayerName) {
		playerName = newplayerName;
	}

	public Square getTopSavePoint() {
		
		return savePoints.get(savePoints.size()-1);
	}
	
	public void removeTopSavePoint() {
		savePoints.remove(savePoints.size()-1);
	}
	
	public ArrayList<Square> getSavePoints() {
		return savePoints;
	}

	
	
	public void addSavePoint(Square s) {
		savePoints.add(s);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWins() {
		return wins;
	}

	public void oneWin() {
		wins++;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void gamePlayed() {
		gamesPlayed++;
	}
	
	
	
	

	

}
