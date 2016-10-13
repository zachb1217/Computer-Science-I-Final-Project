import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StatsGUI extends JFrame implements ActionListener {
	
	private JTable statsTable;
	private JScrollPane pane;
	private String[] columnNames = {"Player", "Wins", "Losses", "Win Ratio"};
	
	
	public StatsGUI(GameInitialization g) {
		this.setTitle("Player Statistics");
		this.setLayout(new BorderLayout());
		this.setSize(400,400);
		JPanel main = new JPanel();
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(Player playa : g.getPlayers()) {
			players.add(playa);
		}
	
		Object[][] data = new Object[players.size()][4];
		
		for(int i = 0; i < players.size(); i++) {
			data[i][0] = players.get(i).getPlayerName();
			data[i][1] = players.get(i).getWins();
			data[i][2] = (players.get(i).getGamesPlayed()-players.get(i).getWins());
			
			int wins = (int) data[i][1];
			int total = players.get(i).getGamesPlayed();
			
			if(total == 0) {
				data[i][3] = 0 + "%";
			}
			
			else {
				data[i][3] = (wins/total)*100 + "%";
			}
					
			}
		
		
		
	
	statsTable = new JTable(data, columnNames);
	
	pane = new JScrollPane(statsTable);
	
	main.add(pane);
	this.add(main, BorderLayout.CENTER);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
