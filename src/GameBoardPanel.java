import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class is responsible for displaying the grid of squares.
 * @author jburge
 * @author zberkowitz
 * @author astone
 * @author agandhi
 *
 */
public class GameBoardPanel extends JPanel {


	private ArrayList<SquarePanel> tiles = new ArrayList<SquarePanel>();

	/**
	 * Create the board and display the squares. 
	 * @param b the board
	 */
	public GameBoardPanel(Board b) {
		
		this.setLayout(new GridLayout(Board.BOARDSIZE,Board.BOARDSIZE));
		Square[][] squares = b.createBoard();
		for (int y=Board.BOARDSIZE-1; y>=0; --y) {
			for (int x=0; x<Board.BOARDSIZE; ++x) {
				if (squares[x][y]!=null) {
					SquarePanel sp = new SquarePanel(squares[x][y]);
					tiles.add(sp);
					this.add(sp);
				}
				else {
					JPanel background = new JPanel();
					background.setBackground(Color.RED);
					background.setOpaque(true);

					this.add(background);
					
				}
			}
		}
	}

	/**
	 * Updates the board as players move around
	 */
	public void update() {
		for (SquarePanel p: tiles) {
			p.update();
		}
		repaint();
	}
	
	//getters and setters
	public ArrayList<SquarePanel> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<SquarePanel> tiles) {
		this.tiles = tiles;
	}
}
