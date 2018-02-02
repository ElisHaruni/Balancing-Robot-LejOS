package pcrunning;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Elis Haruni
 * 
 * This is the GUI for the client with sime simple buttons
 */
public class ClienRCGui {

	private JFrame frame;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienRCGui window = new ClienRCGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClienRCGui() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize()
	{
		
        final BlutoothRCClient RCClient= new BlutoothRCClient();
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(350, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JButton btnForward = new JButton("FORWARD");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.moveForward();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnForward.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnForward.setBounds(109, 11, 100, 80);
		panel.add(btnForward);
		
		JButton btnBackward = new JButton("BACKWARD");
		btnBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.moveBackward();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnBackward.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnBackward.setBounds(109, 95, 100, 80);
		panel.add(btnBackward);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.startBalnancig();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnStart.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnStart.setBounds(5, 11, 100, 80);
		panel.add(btnStart);
		
		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.stop();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnStop.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnStop.setBounds(5, 95, 100, 80);
		panel.add(btnStop);
		
		JButton btnPiruete = new JButton("PIRUETTE");
		btnPiruete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.piruet();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnPiruete.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnPiruete.setBounds(5, 175, 100, 80);
		panel.add(btnPiruete);
		
		JButton btnTurnLeft = new JButton("TURN L");
		btnTurnLeft.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					RCClient.turnLeft();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnTurnLeft.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 11));
		btnTurnLeft.setBounds(109, 175, 100, 80);
		panel.add(btnTurnLeft);
		
		
	}
	
}
