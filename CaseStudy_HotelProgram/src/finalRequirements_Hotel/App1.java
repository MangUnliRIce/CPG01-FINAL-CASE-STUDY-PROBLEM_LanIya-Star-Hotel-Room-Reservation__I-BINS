package finalRequirements_Hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;


public class App1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App1 frame = new App1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App1() {
		setResizable(false);
		setTitle("LStar Hotel Room Reservation System (Admin)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LStarTitle = new JLabel("Lanlya Star Hotel Room Reservation Manager");
		LStarTitle.setForeground(Color.WHITE);
		LStarTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		LStarTitle.setHorizontalAlignment(SwingConstants.CENTER);
		LStarTitle.setBounds(0, 97, 1010, 94);
		contentPane.add(LStarTitle);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(259, 330, 500, 35);
		contentPane.add(formattedTextField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(259, 383, 500, 35);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("USERNAME:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(259, 308, 110, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(259, 371, 110, 12);
		contentPane.add(lblPassword);
		
		JLabel LStarBackground = new JLabel(new ImageIcon(getClass().getResource("DigitalResources/BG1.jpg")));
		LStarBackground.setHorizontalAlignment(SwingConstants.CENTER);
		LStarBackground.setBounds(0, 0, 1020, 693);
		contentPane.add(LStarBackground);
	}
}
