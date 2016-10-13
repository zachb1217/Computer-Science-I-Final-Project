import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The Square class is a data class. The SquarePanel class is a boundary class
 * that is responsible for displaying the appropriate informaiton about each Square
 * @author jburge
 *
 */
public class SquarePanel extends JPanel {
	
	private Square model;
	private JPanel subPanel;
	
	public SquarePanel(Square s) {
		super();
		model = s;
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.PINK);
		this.setOpaque(true);
		JPanel topPanel = new JPanel();
		
		topPanel.setOpaque(false);
		
		
		topPanel.add(new JLabel(s.getLabel()));
		this.add(topPanel,BorderLayout.NORTH);
		
		subPanel = new JPanel();
		subPanel.setOpaque(false);
		this.add(subPanel,BorderLayout.CENTER);
		update();
		
	}

	/**
	 * Updates all the squares so the player labels are in the right spot
	 */
	public void update() {
		subPanel.removeAll();
		
		this.addNames();
		this.validate();
		this.repaint();
	}
	
	
	
	public void addNames() {
		
		ArrayList<Player> temp = model.getPlayersOn();
		
		for(int i = 0; i < temp.size(); i++)	
			subPanel.add(new JLabel(temp.get(i).getPlayerName()));
			
			
		}
	
	/**
	 * This method is just for testing.
	 * @param args
	 */
	public static void main(String [] args) {
		JFrame frame = new JFrame("Hello");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,200);
		frame.setLayout(new GridLayout(2,1));
		frame.add(new SquarePanel(new PlainSquare()));
		frame.add(new SquarePanel(new PlainSquare()));
		frame.setVisible(true);
		
	}

	public Square getModel() {
		return model;
	}

	public void setModel(Square model) {
		this.model = model;
	}
	
	
	
	

}
