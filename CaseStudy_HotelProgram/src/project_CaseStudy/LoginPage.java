package project_CaseStudy;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel LStar_background; // <-- Declare here

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
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
    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel LStar_header = new JLabel("Lanlya Hotel Reservation Manager");
        LStar_header.setFont(new Font("Tahoma", Font.BOLD, 30));
        LStar_header.setHorizontalAlignment(SwingConstants.CENTER);
        LStar_header.setBounds(0, -12, 1020, 113);
        contentPane.add(LStar_header);

        LStar_background = new JLabel(new ImageIcon(LoginPage.class.getResource("/project_CaseStudy/BG1.jpg")));
        LStar_background.setHorizontalAlignment(SwingConstants.LEFT);
        LStar_background.setBounds(0, 0, 1010, 683);

        // Add background last so it sits behind other components
        contentPane.add(LStar_background);
    }
}