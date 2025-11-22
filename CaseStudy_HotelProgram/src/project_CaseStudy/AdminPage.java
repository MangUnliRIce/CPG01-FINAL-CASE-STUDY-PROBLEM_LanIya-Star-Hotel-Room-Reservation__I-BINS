package project_CaseStudy;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel LStar_background; // <-- Declare here
    private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setTitle("Lanlya Hotel Reservation Manager - Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel LStar_header = new JLabel("Welcome Admin " + "Name" + "!");
        LStar_header.setBounds(0, -12, 1020, 113);
        LStar_header.setFont(new Font("Tahoma", Font.BOLD, 30));
        LStar_header.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(LStar_header);
        
        JPanel LStar_TablePanel = new JPanel();
        LStar_TablePanel.setBackground(new Color(255, 215, 0));
        LStar_TablePanel.setBounds(10, 167, 990, 414);
        contentPane.add(LStar_TablePanel);
        LStar_TablePanel.setLayout(null);
        
        table = new JTable();
        table.setBounds(10, 10, 970, 394);
        LStar_TablePanel.add(table);
        
                LStar_background = new JLabel(new ImageIcon(LoginPage.class.getResource("/project_CaseStudy/BG1.jpg")));
                LStar_background.setBounds(0, 0, 1010, 683);
                LStar_background.setHorizontalAlignment(SwingConstants.LEFT);
                
                        // Add background last so it sits behind other components
                        contentPane.add(LStar_background);
	}
}
