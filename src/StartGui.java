import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGui extends JFrame implements ActionListener {

	private JButton continueButton;
	private JPanel panel = new JPanel();
	
	private ImageIcon startImage;
	
	public StartGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Click anywhere to continue");
		this.setSize(1250, 725);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		startImage = new ImageIcon(getClass().getResource("TheBachelor.jpg"));
		continueButton = new JButton(startImage);
		
		continueButton.addActionListener(this);
		continueButton.setActionCommand("Continue");
		panel.add(continueButton);
		
		this.add(panel);
		
		
	}
	

	
	public static void main(String[] args) {
		
		File carelessWhisper = new File("CarelessWhisper.WAV");
		
		StartGui gui = new StartGui();
		gui.setVisible(true);
	
			playSound(carelessWhisper);
		
		
		
	}
	
	public static void playSound(File sound) {
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e) {
			
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Continue")) {
			this.setVisible(false);
			GameInitialization newGame = new GameInitialization();
			newGame.pack();
			newGame.setLocationRelativeTo(null);
			newGame.setVisible(true);
			
		}
		
	}
}
